package com.favorites.remote.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.favorites.domain.Favorites;

import io.favorites.common.api.BasicType;
import io.favorites.common.api.ListResult;

@FeignClient(url = "${favorites.services.folder.address}", name = "folder-service")
public interface FolderFeignService {

    @RequestMapping(value = "/folders/{id}")
    Favorites findById(@PathVariable("id") long id);

    @RequestMapping(value = "/folders/search/findByUserId")
    ListResult<Favorites> findByUserId(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/folders/search/findByUserIdOrderByLastModifyTimeDesc")
    ListResult<Favorites> findByUserIdOrderByLastModifyTimeDesc(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/folders/search/findByUserIdOrderByLastModifyTimeAsc")
    ListResult<Favorites> findByUserIdOrderByLastModifyTimeAsc(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/folders/search/findByUserIdAndName")
    Favorites findByUserIdAndName(@RequestParam("userId") Long userId, @RequestParam("name") String name);

    @RequestMapping(value = "/folders/search/reduceCountById")
    void reduceCountById(@RequestParam("id") Long id, @RequestParam("lastModifyTime") Long lastModifyTime);

    @RequestMapping(value = "/folders/search/updateNameById")
    void updateNameById(@RequestParam("id") Long id, @RequestParam("lastModifyTime") Long lastModifyTime,
            @RequestParam("name") String name);

    @RequestMapping(value = "/folderView/search/findIdByName")
    ListResult<BasicType<Long>> findIdByName(@RequestParam("name") String name);

    @RequestMapping(value = "/folders", method = { RequestMethod.POST })
    Favorites save(Favorites favorites);

    @RequestMapping(value = "/folders/{id}", method = { RequestMethod.DELETE })
    void deleteById(@PathVariable("id") long id);
}
