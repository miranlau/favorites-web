package io.favorites.repository;

import io.favorites.domain.LookRecord;
import io.favorites.domain.view.CollectOfLookRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "lookRecordView", collectionResourceRel = "data", excerptProjection = CollectOfLookRecord.class)
public interface CollectViewRepository extends CrudRepository<LookRecord, Long> {

    public String baseSql = "select r from LookRecord r WHERE r.collect.isDelete='NO'";

    @Query(baseSql + " and r.user.id=?1")
    Page<LookRecord> findViewByUserId(@Param(value = "userId")Long userId, Pageable pageable);

}