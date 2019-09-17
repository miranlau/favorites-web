package io.favorites.service;

import io.favorites.domain.Notice;

public interface NoticeService {
	
	public Notice saveNotice(String collectId, String type, Long userId, String operId);
}
