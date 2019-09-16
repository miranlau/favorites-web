package io.favorites.bookmark.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.favorites.bookmark.domain.Collect;
import io.favorites.bookmark.domain.enums.CollectType;
import io.favorites.bookmark.domain.view.CollectView;

@RepositoryRestResource(path = "collectView", collectionResourceRel = "data", excerptProjection = CollectView.class)
public interface CollectViewRepository extends JpaRepository<Collect, Long> {

    public String baseSql = "select c from Collect c WHERE c.isDelete='NO'";

    public String isDeleteBaseSql = "select c from Collect c WHERE c.isDelete='YES'";

    //随便看看根据类别查询收藏
    @Query(baseSql + " and c.type='public' and c.category=?1 ")
    Page<Collect> findExploreViewByCategory(@Param("category") String category, Pageable pageable);

    //随便看看查询收藏
    @Query(baseSql + " and c.type='public' ")
    Page<Collect> findExploreViewByType(Pageable pageable);

    @Override
    @Transactional
    @RestResource(exported = false)
    void deleteById(Long id);

    @Query(baseSql + " and c.user.id=?1 ")
    Page<Collect> findViewByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(isDeleteBaseSql + " and c.user.id=?1 ")
    Page<Collect> findViewByUserIdAndIsDelete(@Param("userId") Long userId, Pageable pageable);

    @Query(baseSql + " and c.user.id=?1 and c.type=?2")
    Page<Collect> findViewByUserIdAndType(@Param("userId") Long userId, Pageable pageable,
            @Param("type") CollectType type);

    @Query(baseSql + " and c.user.id=?1 and c.type=?2 and c.favorites.id=?3")
    Page<Collect> findViewByUserIdAndTypeAndFavoritesId(@Param("userId") Long userId, Pageable pageable,
            @Param("type") CollectType type, @Param("favoritesId") Long favoritesId);

    @Query(baseSql + " and c.favorites.id=?1 ")
    Page<Collect> findViewByFavoritesId(@Param("favoritesId") Long favoritesId, Pageable pageable);

    @Query(baseSql + " and c.type='public' and c.user.id!=?1 ")
    Page<Collect> findExploreView(@Param("userId") Long userId, Pageable pageable);

    @Query(baseSql + " and (c.user.id=?1 or ( c.user.id in ?2 and c.type='PUBLIC' )) ")
    Page<Collect> findViewByUserIdAndFollows(@Param("userId") Long userId, @Param("userIds") List<Long> userIds,
            Pageable pageable);

    @Query(baseSql + " and c.user.id=?1 and ( c.title like ?2 or c.description like ?2) ")
    Page<Collect> searchMyByKey(@Param("userId") Long userId, @Param("key") String key, Pageable pageable);

    @Query(baseSql + " and c.type='public' and c.user.id!=?1 and ( c.title like ?2 or c.description like ?2) ")
    Page<Collect> searchOtherByKey(@Param("userId") Long userId, @Param("key") String key, Pageable pageable);

    @RestResource(exported = false)
    Collect findById(long id);

}
