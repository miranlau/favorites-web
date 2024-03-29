package com.favorites.web;

import com.favorites.domain.Collect;
import com.favorites.domain.Comment;
import com.favorites.domain.User;
import com.favorites.domain.result.Response;
import com.favorites.remote.BookmarkService;
import com.favorites.remote.CommentService;
import com.favorites.remote.UserService;
import com.favorites.service.NoticeService;
import com.favorites.utils.DateUtils;
import com.favorites.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController{
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Resource
	private NoticeService noticeService;
	@Autowired
    private BookmarkService colloectRepository;
	
	
	/**
	 * @author neo
	 * @date 2016年8月26日
	 * @param comment
	 * @return
	 */
	@RequestMapping(value="/add")
	public Response add(Comment comment) {
		User user = null;
		if (comment.getContent().indexOf("@") > -1) {
			List<String> atUsers = StringUtil.getAtUser(comment.getContent());
			if(atUsers!=null && atUsers.size()>0){
				user = userService.findByUserName(atUsers.get(0));
				if (null != user) {
					comment.setReplyUserId(user.getId());
				} else {
					logger.info("为找到匹配：" + atUsers.get(0) + "的用户.");
				}
				String content=comment.getContent().substring(0,comment.getContent().indexOf("@"));
				if(StringUtils.isBlank(content)){
					content=comment.getContent().substring(comment.getContent().indexOf("@")+user.getUserName().length()+1,comment.getContent().length());
				}
				comment.setContent(content);
			}
		}
		comment.setUserId(getUserId());
		comment.setCreateTime(DateUtils.getCurrentTime());
		Comment savedComment = commentService.save(comment);
		if(null != user){
			// 保存消息通知(回复)
			noticeService.saveNotice(String.valueOf(comment.getCollectId()), "comment", user.getId(), String.valueOf(savedComment.getId()));
		}else{
			// 保存消息通知（直接评论）
			Collect collect = colloectRepository.findById(comment.getCollectId());
			if(null != collect){
				noticeService.saveNotice(String.valueOf(comment.getCollectId()), "comment", collect.getUserId(), String.valueOf(savedComment.getId()));
			}
		}
		
		return result();
	}
	
	
	/**
	 * @author neo
	 * @date 2016年8月26日
	 * @param collectId
	 * @return
	 */
	@RequestMapping(value="/list/{collectId}")
	public List<Comment> list(@PathVariable("collectId") long collectId) {
		List<Comment> comments= commentService.findByCollectIdOrderByIdDesc(collectId);
		return convertComment(comments);
	}
	
	
	/**
	 * @author neo
	 * @date 2016年8月26日
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}")
	public Response delete(@PathVariable("id") long id) {
		commentService.deleteById(id);
		return result();
	}

	
	/**
	 * 转化时间和用户名
	 * @author neo
	 * @date 2016年8月26日
	 * @param comments
	 * @return
	 */
	private List<Comment> convertComment(List<Comment> comments) {
		for (Comment comment : comments) {
			User user = userService.findById((long)comment.getUserId());
			comment.setCommentTime(DateUtils.getTimeFormatText(comment.getCreateTime()));
			comment.setUserName(user.getUserName());
			comment.setProfilePicture(user.getProfilePicture());
			if(comment.getReplyUserId()!=null && comment.getReplyUserId()!=0){
				 User replyUser = userService.findById((long)comment.getReplyUserId());
				 comment.setReplyUserName(replyUser.getUserName());
			}
		}
		return comments;
	}
	
}
