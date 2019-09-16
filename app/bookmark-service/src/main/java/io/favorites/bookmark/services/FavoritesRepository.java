package io.favorites.bookmark.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import io.favorites.bookmark.domain.Favorites;

@RepositoryRestResource(path = "favorites", collectionResourceRel = "data")
public interface FavoritesRepository extends CrudRepository<Favorites, Long> {

    @Override
    @RestResource(exported = false)
    List<Favorites> findAll();

    @Override
    @RestResource(exported = false)
    <S extends Favorites> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Favorites> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    Optional<Favorites> findById(Long id);

    @Override
    @RestResource(exported = false)
    boolean existsById(Long id);

    @Override
    @RestResource(exported = false)
    Iterable<Favorites> findAllById(Iterable<Long> ids);

    @Override
    @RestResource(exported = false)
    long count();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(Favorites entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Favorites> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
