package io.favorites.repository;

import io.favorites.domain.LookRecord;
import io.favorites.domain.view.CollectOfLookRecord;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "lookRecordView", collectionResourceRel = "data", excerptProjection = CollectOfLookRecord.class)
public interface CollectViewRepository extends CrudRepository<LookRecord, Long> {

    public String baseSql = "select r from LookRecord r WHERE r.collect.isDelete='NO'";

    @Query(baseSql + " and r.user.id=?1")
    Page<LookRecord> findViewByUserId(@Param(value = "userId")Long userId, Pageable pageable);

    @Override
    @RestResource(exported = false)
    List<LookRecord> findAll();

    @Override
    @RestResource(exported = false)
    <S extends LookRecord> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends LookRecord> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    Optional<LookRecord> findById(Long id);

    @Override
    @RestResource(exported = false)
    boolean existsById(Long id);

    @Override
    @RestResource(exported = false)
    Iterable<LookRecord> findAllById(Iterable<Long> ids);

    @Override
    @RestResource(exported = false)
    long count();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(LookRecord entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends LookRecord> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
