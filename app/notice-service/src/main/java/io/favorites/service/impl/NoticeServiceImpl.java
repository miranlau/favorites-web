package io.favorites.service.impl;

import io.favorites.domain.Collect;
import io.favorites.domain.Comment;
import io.favorites.domain.Notice;
import io.favorites.domain.User;
import io.favorites.repository.NoticeRepository;
import io.favorites.service.NoticeService;
import io.favorites.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	/**
	 * 保存消息通知
	 * @param collectId
	 * @param type
	 * @param userId
	 * @param operId
	 */
	public Notice saveNotice(String collectId, String type, Long userId, String operId){
		Notice notice = new Notice();
		if(StringUtils.isNotBlank(collectId)){
			Collect collect = new Collect();
			collect.setId(Long.valueOf(collectId));
			collect.setUserId(userId);
			notice.setCollect(collect);
		}
		notice.setReaded("unread");
		notice.setType(type);
		if(StringUtils.isNotBlank(operId)){
			Comment comment = new Comment();
			comment.setId(Long.valueOf(operId));
			notice.setComment(comment);
		}
		User user = new User();
		user.setId(userId);
		notice.setUser(user);
		notice.setCreateTime(DateUtils.getCurrentTime());
		Notice savedNotice = noticeRepository.save(notice);
		return savedNotice;
	}
}
