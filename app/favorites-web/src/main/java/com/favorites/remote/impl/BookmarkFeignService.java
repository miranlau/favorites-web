/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.remote.impl;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.favorites.domain.Collect;
import com.favorites.domain.enums.CollectType;
import com.favorites.domain.enums.IsDelete;
import com.favorites.domain.view.CollectViewImpl;

import io.favorites.common.api.ListResult;
import io.favorites.common.api.PageResult;

/**
 * This class is a feign client for Bookmark service.
 */
@FeignClient(url = "${favorites.services.bookmarks.address}", name = "bookmarks-service")
public interface BookmarkFeignService {

    @RequestMapping(value = "/collectView/search/findExploreViewByCategory")
    PageResult<CollectViewImpl> findExploreViewByCategory(@RequestParam("category") String category,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/")
    PageResult<CollectViewImpl> findExploreViewByType(@RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/countByUserIdAndIsDelete")
    Long countByUserIdAndIsDelete(@RequestParam("userId") Long userId, @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/search/countByUserIdAndTypeAndIsDelete")
    Long countByUserIdAndTypeAndIsDelete(@RequestParam("userId") Long userId, @RequestParam("type") CollectType type,
            @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/search/findByIdAndUserId")
    Collect findByIdAndUserId(@RequestParam("id") Long id, @RequestParam("userId") Long userId);

    @RequestMapping(value = "/collects/{id}", method = { RequestMethod.DELETE })
    void deleteById(@PathVariable("id") Long id);

    @RequestMapping(value = "/collects/search/findByFavoritesIdAndIsDelete")
    ListResult<Collect> findByFavoritesIdAndIsDelete(@RequestParam("favoritesId") Long favoritesId,
            @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/search/findByFavoritesIdAndUrlAndUserIdAndIsDelete")
    ListResult<Collect> findByFavoritesIdAndUrlAndUserIdAndIsDelete(@RequestParam("favoritesId") Long favoritesId,
            @RequestParam("url") String url, @RequestParam("userId") Long userId,
            @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/search/modifyByIdAndUserId")
    int modifyByIdAndUserId(@RequestParam("type") CollectType type, @RequestParam("id") Long id,
            @RequestParam("userId") Long userId);

    @RequestMapping(value = "/collects/search/deleteByFavoritesId")
    void deleteByFavoritesId(@RequestParam("favoritesId") Long favoritesId);

    @RequestMapping(value = "/collectView/search/findViewByUserId")
    PageResult<CollectViewImpl> findViewByUserId(@RequestParam("userId") Long userId,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findViewByUserIdAndIsDelete")
    PageResult<CollectViewImpl> findViewByUserIdAndIsDelete(@RequestParam("userId") Long userId,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findViewByUserIdAndType")
    PageResult<CollectViewImpl> findViewByUserIdAndType(@RequestParam("userId") Long userId,
            @RequestParam("type") CollectType type, @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findViewByUserIdAndTypeAndFavoritesId")
    PageResult<CollectViewImpl> findViewByUserIdAndTypeAndFavoritesId(@RequestParam("userId") Long userId,
            @RequestParam("type") CollectType type, @RequestParam("favoritesId") Long favoritesId,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findViewByFavoritesId")
    PageResult<CollectViewImpl> findViewByFavoritesId(@RequestParam("favoritesId") Long favoritesId,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findExploreView")
    PageResult<CollectViewImpl> findExploreView(@RequestParam("userId") Long userId, @RequestParam("page") Integer page,
            @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/findViewByUserIdAndFollows")
    PageResult<CollectViewImpl> findViewByUserIdAndFollows(@RequestParam("userId") Long userId,
            @RequestParam("userIds") List<Long> userIds, @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/searchMyByKey")
    PageResult<CollectViewImpl> searchMyByKey(@RequestParam("userId") Long userId, @RequestParam("key") String key,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collectView/search/searchOtherByKey")
    PageResult<CollectViewImpl> searchOtherByKey(@RequestParam("userId") Long userId, @RequestParam("key") String key,
            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort);

    @RequestMapping(value = "/collects/search/countByFavoritesIdAndTypeAndIsDelete")
    Long countByFavoritesIdAndTypeAndIsDelete(@RequestParam("favoritesId") Long favoritesId,
            @RequestParam("type") CollectType type, @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/search/countByFavoritesIdAndIsDelete")
    Long countByFavoritesIdAndIsDelete(@RequestParam("favoritesId") Long favoritesId, IsDelete isDelete);

    @RequestMapping(value = "/collects/search/findByCreateTimeLessThanAndIsDeleteAndFavoritesIdIn")
    ListResult<Collect> findByCreateTimeLessThanAndIsDeleteAndFavoritesIdIn(@RequestParam("createTime") Long createTime,
            @RequestParam("isDelete") IsDelete isDelete, @RequestParam("favoritesIds") Long[] favoritesIds);

    @RequestMapping(value = "/collects/search/modifyIsDeleteById")
    int modifyIsDeleteById(@RequestParam("isDelete") IsDelete isDelete,
            @RequestParam("lastModifyTime") Long lastModifyTime, @RequestParam("id") Long id);

    @RequestMapping(value = "/collects/search/updateLogoUrlByUrl")
    int updateLogoUrlByUrl(@RequestParam("logoUrl") String logoUrl, @RequestParam("lastModifyTime") Long lastModifyTime,
            @RequestParam("url") String url);

    @RequestMapping(value = "/collects/search/findByUserIdAndIsDelete")
    ListResult<Collect> findByUserIdAndIsDelete(@RequestParam("userId") Long userId,
            @RequestParam("isDelete") IsDelete isDelete);

    @RequestMapping(value = "/collects/{id}")
    Collect findById(@PathVariable("id") long id);

    @RequestMapping(value = "/collects", method = { RequestMethod.POST })
    void save(@RequestBody Collect collect);
}
