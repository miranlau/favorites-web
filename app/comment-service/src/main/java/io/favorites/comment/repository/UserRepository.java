package io.favorites.comment.repository;

import io.favorites.comment.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "users", collectionResourceRel = "data")
public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    @RestResource(exported = false)
    List<User> findAll();

    @Override
    @RestResource(exported = false)
    Optional<User> findById(Long id);
}
