package com.favorites.remote;

import java.util.List;

import com.favorites.domain.Follow;
import com.favorites.domain.User;
import com.favorites.domain.enums.FollowStatus;

public interface FollowService {
	
	void save(Follow follow);

	List<User> findByUserId(Long userId);
	
	List<Follow> findMyFollowIdByUserId(Long userId);
	
	Integer countByUserIdAndStatus(Long userId, FollowStatus status);
	
	Integer countByFollowIdAndStatus(Long followId, FollowStatus status);
	
	List<User> findFollowUserByUserId(Long userId);
	
	List<User> findFollowedUserByFollowId(Long followId);
	
	Integer countByUserIdAndFollowIdAndStatus(Long userId, Long followId, FollowStatus status);
	
	Follow findByUserIdAndFollowId(Long userId, Long followId);
	
	Integer updateStatusById(FollowStatus status, Long lastModifyTime, Long id);
}
