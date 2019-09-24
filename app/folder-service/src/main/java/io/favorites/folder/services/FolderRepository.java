package io.favorites.folder.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.folder.domain.Favorites;

@RepositoryRestResource(path = "folders", collectionResourceRel = "data")
public interface FolderRepository extends JpaRepository<Favorites, Long> {

    Favorites findById(long id);

    List<Favorites> findByUserId(@Param("userId") Long userId);

    List<Favorites> findByUserIdOrderByLastModifyTimeDesc(@Param("userId") Long userId);

    List<Favorites> findByUserIdOrderByLastModifyTimeAsc(@Param("userId") Long userId);

    Favorites findByUserIdAndName(@Param("userId") Long userId, @Param("name") String name);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Favorites f set f.count=(f.count-1),f.lastModifyTime =:lastModifyTime where f.id =:id")
    void reduceCountById(@Param("id") Long id, @Param("lastModifyTime") Long lastModifyTime);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Favorites set name=:name ,lastModifyTime=:lastModifyTime where id=:id")
    void updateNameById(@Param("id") Long id, @Param("lastModifyTime") Long lastModifyTime, @Param("name") String name);

    @Query("select id from Favorites where name=?1")
    List<Favorites> findIdByName(@Param("name") String name);
}
