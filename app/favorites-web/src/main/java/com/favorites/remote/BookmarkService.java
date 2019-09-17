/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.remote;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.favorites.domain.Collect;
import com.favorites.domain.enums.CollectType;
import com.favorites.domain.enums.IsDelete;
import com.favorites.domain.view.CollectView;

/**
 * This interface is to call bookmarks service.
 */
public interface BookmarkService {

    Page<CollectView> findExploreViewByCategory(String category, Pageable pageable);

    Page<CollectView> findExploreViewByType(Pageable pageable);

    Long countByUserIdAndIsDelete(Long userId, IsDelete isDelete);

    Long countByUserIdAndTypeAndIsDelete(Long userId, CollectType type, IsDelete isDelete);

    Collect findByIdAndUserId(Long id, Long userId);

    @Transactional
    void deleteById(Long id);

    List<Collect> findByFavoritesIdAndIsDelete(Long favoritesId, IsDelete isDelete);

    List<Collect> findByFavoritesIdAndUrlAndUserIdAndIsDelete(Long favoritesId, String url, Long userId,
            IsDelete isDelete);

    int modifyByIdAndUserId(CollectType type, Long id, Long userId);

    void deleteByFavoritesId(Long favoritesId);

    Page<CollectView> findViewByUserId(Long userId, Pageable pageable);

    Page<CollectView> findViewByUserIdAndIsDelete(Long userId, Pageable pageable);

    Page<CollectView> findViewByUserIdAndType(Long userId, CollectType type, Pageable pageable);

    Page<CollectView> findViewByUserIdAndTypeAndFavoritesId(Long userId, CollectType type, Long favoritesId,
            Pageable pageable);

    Page<CollectView> findViewByFavoritesId(Long favoritesId, Pageable pageable);

    Page<CollectView> findExploreView(Long userId, Pageable pageable);

    Page<CollectView> findViewByUserIdAndFollows(Long userId, List<Long> userIds, Pageable pageable);

    Page<CollectView> searchMyByKey(Long userId, String key, Pageable pageable);

    Page<CollectView> searchOtherByKey(Long userId, String key, Pageable pageable);

    Long countByFavoritesIdAndTypeAndIsDelete(Long favoritesId, CollectType type, IsDelete isDelete);

    Long countByFavoritesIdAndIsDelete(Long favoritesId, IsDelete isDelete);

    List<Collect> findByCreateTimeLessThanAndIsDeleteAndFavoritesIdIn(Long createTime, IsDelete isDelete,
            Long[] favoritesIds);

    int modifyIsDeleteById(IsDelete isDelete, Long lastModifyTime, Long id);

    int updateLogoUrlByUrl(String logoUrl, Long lastModifyTime, String url);

    List<Collect> findByUserIdAndIsDelete(Long userId, IsDelete isDelete);

    Collect findById(long id);

    void save(Collect collect);

    // move this function from NoticeRepository.findViewByUserIdAndType
    Page<CollectView> findViewByUserIdAndNoticeType(Long userId, String noticeType, Pageable pageable);
}
