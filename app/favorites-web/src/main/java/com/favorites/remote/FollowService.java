package com.favorites.remote;

import java.util.List;

import com.favorites.domain.Follow;
import com.favorites.domain.enums.FollowStatus;

public interface FollowService {
	
	void save(Follow follow);

	List<String> findByUserId(Long userId);
	
	List<Long> findMyFollowIdByUserId(Long userId);
	
	Integer countByUserIdAndStatus(Long userId, FollowStatus status);
	
	Integer countByFollowIdAndStatus(Long followId, FollowStatus status);
	
	List<String> findFollowUserByUserId(Long userId);
	
	List<String> findFollowedUserByFollowId(Long followId);
	
	Integer countByUserIdAndFollowIdAndStatus(Long userId, Long followId, FollowStatus status);
	
	Follow findByUserIdAndFollowId(Long userId, Long followId);
	
	Integer updateStatusById(FollowStatus status, Long lastModifyTime, Long id);
}
