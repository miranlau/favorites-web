package com.favorites.web;

import com.favorites.comm.aop.LoggerManage;
import com.favorites.domain.Comment;
import com.favorites.domain.Notice;
import com.favorites.domain.result.ExceptionMsg;
import com.favorites.domain.result.Response;
import com.favorites.domain.result.ResponseData;
import com.favorites.domain.view.CollectSummary;
import com.favorites.remote.CommentService;
import com.favorites.remote.impl.NoticeFeignService;
import com.favorites.service.NoticeService;
import com.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private NoticeFeignService noticeFeignService;

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 回复
	 * @param comment
	 * @return
	 */
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public Response reply(Comment comment) {
		logger.info("reply begin");
		try {
			comment.setUserId(getUserId());
			comment.setCreateTime(DateUtils.getCurrentTime());
			Comment saveCommon = commentService.save(comment);
			Notice notice = new Notice();
			notice.setCollectId(comment.getCollectId() + "");
			notice.setUserId(comment.getReplyUserId());
			notice.setType("comment");
			notice.setReaded("unread");
			notice.setOperId(saveCommon.getId().toString());
			notice.setCreateTime(DateUtils.getCurrentTime());
			noticeFeignService.saveNotice(String.valueOf(comment.getCollectId()), "comment", notice.getUserId(), notice.getOperId());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("reply failed, ", e);
			return result(ExceptionMsg.FAILED);
		}
		return result();
	}

	@RequestMapping(value="/getNoticeNum")
	@LoggerManage(description="获取新消息数量")
	public ResponseData getNoticeNum(){
		Map<String,Long> result = new HashMap<String, Long>();
		Long newAtMeCount = noticeFeignService.countByUserIdAndTypeAndReaded(getUserId(), "at", "unread");
		Long newCommentMeCount = noticeFeignService.countByUserIdAndTypeAndReaded(getUserId(), "comment", "unread");
		Long newPraiseMeCount = noticeFeignService.countPraiseByUserIdAndReaded(getUserId(), "unread");
		Long newLetterNotice = noticeFeignService.countByUserIdAndTypeAndReaded(getUserId(),"letter","unread");
		result.put("newAtMeCount",newAtMeCount);
		result.put("newCommentMeCount",newCommentMeCount);
		result.put("newPraiseMeCount",newPraiseMeCount);
		result.put("newLetterNotice",newLetterNotice);
		return new ResponseData(ExceptionMsg.SUCCESS,result);
	}

	@RequestMapping(value="/create", method = RequestMethod.POST)
	@LoggerManage(description="Create new notice.")
	public Notice createNotice(String collectId,String type,Long userId,String operId){
		Notice newNotice = noticeService.saveNotice(collectId, type, userId, operId);
		return newNotice;
	}

	@RequestMapping(value="/collects", method = RequestMethod.POST)
	@LoggerManage(description="Get all collects by notice.")
	public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable) {
		return noticeService.getNoticeCollects(type, userId, pageable);
	}

}
