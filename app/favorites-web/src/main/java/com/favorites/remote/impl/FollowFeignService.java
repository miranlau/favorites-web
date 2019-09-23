package com.favorites.remote.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.favorites.domain.Follow;
import com.favorites.domain.enums.FollowStatus;

@FeignClient(url = "${favorites.services.follow.address}", name = "follow-service")
public interface FollowFeignService {

	@RequestMapping(value = "/follows", method = { RequestMethod.POST })
	void save(@RequestBody Follow follow);
	
	@RequestMapping(value = "/search/findByUserId")
	List<String> findByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/search/findMyFollowIdByUserId")
	List<Long> findMyFollowIdByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/search/countByUserIdAndStatus")
	Integer countByUserIdAndStatus(@RequestParam("userId") Long userId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/search/countByFollowIdAndStatus")
	Integer countByFollowIdAndStatus(@RequestParam("followId") Long followId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/search/findFollowUserByUserId")
	List<String> findFollowUserByUserId(@RequestParam("userId") Long userId);
	
	@RequestMapping(value = "/search/findFollowedUserByFollowId")
	List<String> findFollowedUserByFollowId(@RequestParam("followId") Long followId);
	
	@RequestMapping(value = "/search/countByUserIdAndFollowIdAndStatus")
	Integer countByUserIdAndFollowIdAndStatus(@RequestParam("userId") Long userId, @RequestParam("followId") Long followId, @RequestParam("status") FollowStatus status);
	
	@RequestMapping(value = "/search/findByUserIdAndFollowId")
	Follow findByUserIdAndFollowId(@RequestParam("userId") Long userId, @RequestParam("followId") Long followId);
	
	@RequestMapping(value = "/search/updateStatusById")
	Integer updateStatusById(@RequestParam("status") FollowStatus status, @RequestParam("lastModifyTime") Long lastModifyTime, @RequestParam("id") Long id);
}
