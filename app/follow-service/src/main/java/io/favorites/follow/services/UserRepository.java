package io.favorites.follow.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.follow.domain.User;

@RepositoryRestResource(collectionResourceRel = "data", path = "user")
public interface UserRepository extends JpaRepository<User, Long> {

}
