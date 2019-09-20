package io.favorites.repository;

import io.favorites.domain.Collect;
import io.favorites.domain.enums.CollectType;
import io.favorites.domain.enums.IsDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(path = "collects", collectionResourceRel = "data")
public interface CollectRepository extends JpaRepository<Collect, Long> {

    Long countByUserIdAndIsDelete(@Param("userId") Long userId, @Param("isDelete") IsDelete isDelete);

    Long countByUserIdAndTypeAndIsDelete(@Param("userId") Long userId, @Param("type") CollectType type,
                                         @Param("isDelete") IsDelete isDelete);


    @Override
    @Transactional
    void deleteById(Long id);

    //	Page<Collect> findByFavoritesIdAndIsDelete(Long favoritesId,Pageable pageable,IsDelete isDelete);

    List<Collect> findByFavoritesIdAndIsDelete(@Param("favoritesId") Long favoritesId,
                                                @Param("isDelete") IsDelete isDelete);

    List<Collect> findByFavoritesIdAndUrlAndUserIdAndIsDelete(@Param("favoritesId") Long favoritesId,
                                                               @Param("url") String url, @Param("userId") Long userId, @Param("isDelete") IsDelete isDelete);

    @Transactional
    @Modifying
    @Query("update Collect c set c.type = ?1 where c.id = ?2 and c.user.id=?3 ")
    int modifyByIdAndUserId(@Param("type") CollectType type, @Param("id") Long id, @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("delete from Collect where favoritesId = ?1")
    void deleteByFavoritesId(@Param("favoritesId") Long favoritesId);

    Long countByFavoritesIdAndTypeAndIsDelete(@Param("favoritesId") Long favoritesId, @Param("type") CollectType type,
                                              @Param("isDelete") IsDelete isDelete);

    Long countByFavoritesIdAndIsDelete(@Param("favoritesId") Long favoritesId, @Param("isDelete") IsDelete isDelete);

    List<Collect> findByCreateTimeLessThanAndIsDeleteAndFavoritesIdIn(@Param("createTime") Long createTime,
                                                                       @Param("isDelete") IsDelete isDelete, @Param("favoritesIds") List<Long> favoritesIds);

    @Transactional
    @Modifying
    @Query("update Collect c set c.isDelete = ?1,c.lastModifyTime = ?2 where c.id = ?3")
    int modifyIsDeleteById(@Param("isDelete") IsDelete isDelete, @Param("lastModifyTime") Long lastModifyTime,
                           @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update Collect c set c.logoUrl = ?1,c.lastModifyTime = ?2 where c.url = ?3")
    int updateLogoUrlByUrl(@Param("logoUrl") String logoUrl, @Param("lastModifyTime") Long lastModifyTime,
                           @Param("url") String url);

    List<Collect> findByUserIdAndIsDelete(@Param("userId") Long userId, @Param("isDelete") IsDelete isDelete);

    Collect findById(@Param("userId") long id);

}
