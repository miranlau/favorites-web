package io.favorites.bookmark.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.favorites.bookmark.domain.Notice;
import io.favorites.bookmark.domain.view.CollectOfNoticeView;

@RepositoryRestResource(collectionResourceRel = "data", excerptProjection = CollectOfNoticeView.class)
public interface NoticeRepository extends CrudRepository<Notice, Long> {

    @Query
    Page<Notice> findByUserIdAndType(@Param("userId") Long userId, @Param("type") String type, Pageable pageable);

    @Override
    @RestResource(exported = false)
    List<Notice> findAll();

    @Override
    @RestResource(exported = false)
    <S extends Notice> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Notice> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    Optional<Notice> findById(Long id);

    @Override
    @RestResource(exported = false)
    boolean existsById(Long id);

    @Override
    @RestResource(exported = false)
    Iterable<Notice> findAllById(Iterable<Long> ids);

    @Override
    @RestResource(exported = false)
    long count();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Notice entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Notice> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();

}
