package io.favorites.repository;

import io.favorites.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @RestResource(exported = false)
    List<User> findAll();

    @Override
    @RestResource(exported = false)
    <S extends User> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends User> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    Optional<User> findById(Long id);

    @Override
    @RestResource(exported = false)
    boolean existsById(Long id);

    @Override
    @RestResource(exported = false)
    Iterable<User> findAllById(Iterable<Long> ids);

    @Override
    @RestResource(exported = false)
    long count();

    @Override
    @RestResource(exported = false)
    void deleteById(Long id);

    @Override
    @RestResource(exported = false)
    void delete(User entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends User> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
