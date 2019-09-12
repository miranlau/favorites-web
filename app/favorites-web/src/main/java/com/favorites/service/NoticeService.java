package com.favorites.service;

import com.favorites.domain.Notice;
import com.favorites.domain.view.CollectSummary;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
	public Notice saveNotice(String collectId, String type, Long userId, String operId);

	public List<CollectSummary> getNoticeCollects(String type, Long userId, Pageable pageable);

	public int updateReadedByUserId(String readed, long userId, String type);

}
