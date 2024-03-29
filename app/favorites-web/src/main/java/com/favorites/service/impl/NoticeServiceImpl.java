package com.favorites.service.impl;

import com.favorites.domain.Notice;
import com.favorites.domain.User;
import com.favorites.domain.view.CollectSummary;
import com.favorites.domain.view.CollectView;
import com.favorites.domain.view.CommentView;
import com.favorites.remote.BookmarkService;
import com.favorites.remote.PraiseService;
import com.favorites.remote.CommentService;
import com.favorites.remote.impl.NoticeFeignService;
import com.favorites.repository.UserRepository;
import com.favorites.service.NoticeService;
import com.favorites.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeFeignService noticeFeignService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PraiseService praiseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookmarkService bookmarkService;

    /**
     * 保存消息通知
     *
     * @param collectId
     * @param type
     * @param userId
     * @param operId
     */
    public Notice saveNotice(String collectId, String type, Long userId, String operId) {
        Notice notice = new Notice();
        if (StringUtils.isNotBlank(collectId)) {
            notice.setCollectId(collectId);
        }
        notice.setReaded("unread");
        notice.setType(type);
        if (StringUtils.isNotBlank(operId)) {
            notice.setOperId(operId);
        }
        notice.setUserId(userId);
        notice.setCreateTime(DateUtils.getCurrentTime());
        Notice savedNotice = noticeFeignService.saveNotice(collectId, type, userId, operId);
        return savedNotice;
    }

    @Override
    public int updateReadedByUserId(String readed, long userId, String type) {
        return noticeFeignService.updateReadedByUserId(readed, userId, type);
    }

    /**
     * 展示消息通知
     *
     * @param type
     * @param userId
     * @param pageable
     */
    public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable) {
        // TODO Auto-generated method stub
        Page<CollectView> views = bookmarkService.findViewByUserIdAndNoticeType(userId, type, pageable);
        return convertCollect(views, type);
    }


    private List<CollectSummary> convertCollect(Page<CollectView> views, String type) {
        List<CollectSummary> summarys = new ArrayList<CollectSummary>();
        for (CollectView view : views) {
            CollectSummary summary = new CollectSummary(view);
            if ("at".equals(type)) {
                summary.setCollectTime(DateUtils.getTimeFormatText(view.getLastModifyTime()) + " at了你");
            } else if ("comment".equals(type)) {
            	CommentView comment = commentService.findReplyUser(Long.valueOf(view.getOperId()));
                if (comment == null) {
                    continue;
                }
                summary.setUserId(comment.getUserId());
                summary.setUserName(comment.getUserName());
                summary.setProfilePicture(comment.getProfilePicture());
                if (comment.getReplyUserId() != null && comment.getReplyUserId() != 0) {
                    User replyUser = userRepository.findById(comment.getReplyUserId().longValue());
                    summary.setRemark("回复@" + replyUser.getUserName() + ": " + comment.getContent());
                } else {
                    summary.setRemark(comment.getContent());
                }
                summary.setCollectTime(DateUtils.getTimeFormatText(comment.getCreateTime()));
            } else if ("praise".equals(type)) {
            	CommentView comment = praiseService.findPraiseUser(Long.valueOf(view.getOperId()));
                if (comment == null) {
                    continue;
                }
                summary.setUserId(comment.getUserId());
                summary.setUserName(comment.getUserName());
                summary.setProfilePicture(comment.getProfilePicture());
                summary.setCollectTime(DateUtils.getTimeFormatText(comment.getCreateTime()) + " 赞了你的收藏");
            }
            summarys.add(summary);
        }
        return summarys;
    }
}
