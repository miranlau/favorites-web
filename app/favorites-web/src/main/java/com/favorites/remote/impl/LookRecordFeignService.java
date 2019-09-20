package com.favorites.remote.impl;

import com.favorites.domain.LookRecord;
import com.favorites.domain.view.CollectViewImpl;
import io.favorites.common.api.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 浏览记录LookRecordFeignService is suporting to LookRecordService
 */
@FeignClient(name = "look-record-service", url="${favorites.services.lookrecord.address}")
public interface LookRecordFeignService {

    @GetMapping("/lookRecordView/search/findViewByUserId")
    PageResult<CollectViewImpl> findViewByUserId(@RequestParam("userId") Long userId, @RequestParam("page") Integer page,
                                                 @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @GetMapping("/lookRecords/search/countByUserIdAndCollectId")
    Integer countByUserIdAndCollectId(@RequestParam("userId") Long userId, @RequestParam("collectId") Long collectId);

    @GetMapping("/lookRecords/search/updatelastModifyTime")
    void updatelastModifyTime(@RequestParam("userId") Long userId, @RequestParam("collectId") Long collectId, @RequestParam("lastModifyTime") Long lastModifyTime);

    @GetMapping("/lookRecords/search/deleteByUserIdAndCollectId")
    int deleteByUserIdAndCollectId(@RequestParam("userId") Long userId, @RequestParam("collectId") Long collectId);

    @GetMapping("/lookRecords/search/deleteByUserId")
    int deleteByUserId(@RequestParam("userId") Long userId);

    @GetMapping("/lookRecords/save")
    LookRecord save(@RequestBody LookRecord lookRecord);
}
