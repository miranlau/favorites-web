package com.favorites.remote.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.favorites.domain.Follow;
import com.favorites.domain.User;
import com.favorites.domain.enums.FollowStatus;

import io.favorites.common.api.ListResult;

@FeignClient(url = "${favorites.services.follow.address}", name = "follow-service")
public interface FollowFeignService {

	@RequestMapping(value = "/follows", method = { RequestMethod.POST })
	void save(@RequestBody Follow follow);
	
	@RequestMapping(value = "/follows/search/findByUserId")
	ListResult<User> findByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/follows/search/findMyFollowIdByUserId")
	ListResult<Follow> findMyFollowIdByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/follows/search/countByUserIdAndStatus")
	Integer countByUserIdAndStatus(@RequestParam("userId") Long userId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/follows/search/countByFollowIdAndStatus")
	Integer countByFollowIdAndStatus(@RequestParam("followId") Long followId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/follows/search/findFollowUserByUserId")
	ListResult<User> findFollowUserByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/follows/search/findFollowedUserByFollowId")
	ListResult<User> findFollowedUserByFollowId(@RequestParam("followId") Long followId);
	
	@RequestMapping(value = "/follows/search/countByUserIdAndFollowIdAndStatus")
	Integer countByUserIdAndFollowIdAndStatus(@RequestParam("userId") Long userId, @RequestParam("followId") Long followId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/follows/search/findByUserIdAndFollowId")
	Follow findByUserIdAndFollowId(@RequestParam("userId") Long userId, @RequestParam("followId") Long followId);
	
	@RequestMapping(value = "/follows/search/updateStatusById")
	Integer updateStatusById(@RequestParam("status") FollowStatus status, @RequestParam("lastModifyTime") Long lastModifyTime, @RequestParam("id") Long id);
}
