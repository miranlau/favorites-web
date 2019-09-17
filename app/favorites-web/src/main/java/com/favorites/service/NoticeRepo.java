package com.favorites.service;

import com.favorites.domain.Notice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "notice-service", path="/notices", url="#{'${notice-service.url}'}")
public interface NoticeRepo {
	@PostMapping("/search/updateReadedByUserId")
	int updateReadedByUserId(@RequestParam(value = "readed")String readed, @RequestParam(value = "userId")long userId, @RequestParam(value = "type")String type);

	@GetMapping("/search/countByUserIdAndTypeAndReaded")
	Long countByUserIdAndTypeAndReaded(@RequestParam("userId")Long userId, @RequestParam("type")String type, @RequestParam("readed")String readed);

	@GetMapping("/search/countPraiseByUserIdAndReaded")
	Long countPraiseByUserIdAndReaded(@RequestParam("userId")Long userId,@RequestParam("readed")String readed);

	@PostMapping("/create")
	public Notice saveNotice(@RequestParam("collectId")String collectId,
					   @RequestParam("type")String type,
					   @RequestParam("userId")Long userId,
					   @RequestParam("operId")String operId);

}