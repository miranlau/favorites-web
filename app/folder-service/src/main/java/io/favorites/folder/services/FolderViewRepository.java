package io.favorites.folder.services;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.folder.domain.Favorites2;
import io.favorites.folder.domain.custom.FolderIdView;

@RepositoryRestResource(path = "folderView", collectionResourceRel = "data", excerptProjection = FolderIdView.class)
public interface FolderViewRepository extends JpaRepository<Favorites2, Long> {

    @Query("select f from Favorites2 f where name=?1")
    List<Favorites2> findIdByName(@Param("name") String name);
}
