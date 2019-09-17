package io.favorites.controller;

import io.favorites.comm.aop.LoggerManage;
import io.favorites.domain.Collect;
import io.favorites.domain.Comment;
import io.favorites.domain.Notice;
import io.favorites.domain.User;
import io.favorites.domain.result.ExceptionMsg;
import io.favorites.domain.result.Response;
import io.favorites.domain.result.ResponseData;
import io.favorites.repository.NoticeRepository;
import io.favorites.service.NoticeService;
import io.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/notices")
public class NoticeController extends BaseController {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private NoticeService noticeService;

    public static final String NOTICE_READ = "read";
    public static final String NOTICE_TYPE_PRAISE = "praise";
    public static final String NOTICE_TYPE_COMMENT = "comment";
    public static final String NOTICE_TYPE_AT = "at";

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public Response replyNotice(@RequestBody Comment comment) {
		logger.info("reply begin");
		try {
			comment.setUserId(getUserId());
			comment.setCreateTime(DateUtils.getCurrentTime());
            //Calling shall save comment then come here to save notice.
//			Comment saveCommon = commentRepository.save(comment);
			Notice notice = new Notice();
			Collect collect = new Collect();
			collect.setId(comment.getCollectId());
			notice.setCollect(collect);
			User user = new User();
            user.setId(comment.getReplyUserId());
			notice.setUser(user);
			notice.setType("comment");
			notice.setReaded("unread");
//			notice.setOperId(saveCommon.getId().toString());
			notice.setComment(comment);
			notice.setCreateTime(DateUtils.getCurrentTime());
			noticeRepository.save(notice);
		} catch (Exception e) {
			logger.error("reply failed, ", e);
			return result(ExceptionMsg.FAILED);
		}
		return result();
	}

    @RequestMapping(value = "/getNoticeNum")
    @LoggerManage(description = "Get new notice count.")
    public ResponseData getNoticeNum() {
        Map<String, Long> result = new HashMap<String, Long>();
        Long newAtMeCount = noticeRepository.countByUserIdAndTypeAndReaded(getUserId(), "at", "unread");
        Long newCommentMeCount = noticeRepository.countByUserIdAndTypeAndReaded(getUserId(), "comment", "unread");
        Long newPraiseMeCount = noticeRepository.countPraiseByUserIdAndReaded(getUserId(), "unread");
        Long newLetterNotice = noticeRepository.countByUserIdAndTypeAndReaded(getUserId(), "letter", "unread");
        result.put("newAtMeCount", newAtMeCount);
        result.put("newCommentMeCount", newCommentMeCount);
        result.put("newPraiseMeCount", newPraiseMeCount);
        result.put("newLetterNotice", newLetterNotice);
        return new ResponseData(ExceptionMsg.SUCCESS, result);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @LoggerManage(description = "Create new notice by parameters.")
    public ResponseData createNotice(@RequestParam(value = "collectId", required = false) String collectId,
                                     @RequestParam(value = "type") String type,
                                     @RequestParam(value = "userId") Long userId,
                                     @RequestParam(value = "operId", required = false) String operId) {
        Notice newNotice = noticeService.saveNotice(collectId, type, userId, operId);
        Map<String, Notice> result = new HashMap<String, Notice>();
        result.put("newNotice", newNotice);
        return new ResponseData(ExceptionMsg.SUCCESS, result);
    }
}
