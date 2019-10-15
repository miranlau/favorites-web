package com.favorites.remote.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.favorites.domain.Praise;
import com.favorites.domain.view.CommentView;

@FeignClient(url = "${favorites.services.praise.address}", name = "praise-service")
public interface PraiseFeignService {
	
	@RequestMapping(value = "/praise", method = { RequestMethod.POST })
    Praise save(@RequestBody Praise praise);
	
	@RequestMapping(value = "/praise/search/countByCollectId")
	Long countByCollectId(@RequestParam("collectId") Long collectId);

	@RequestMapping(value = "/praise/search/findByUserIdAndCollectId")
	Praise findByUserIdAndCollectId(@RequestParam("userId") Long userId, @RequestParam("collectId") Long collectId);

	@RequestMapping(value = "/praise/search/findPraiseUser")
    CommentView findPraiseUser(@RequestParam("id") Long id);

	@RequestMapping(value = "/praise/{id}", method = { RequestMethod.DELETE })
	void deleteById(@PathVariable("id") Long id);
}
