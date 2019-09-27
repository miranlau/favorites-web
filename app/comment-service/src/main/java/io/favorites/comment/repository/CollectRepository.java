package io.favorites.comment.repository;

import io.favorites.comment.domain.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "collects", collectionResourceRel = "data")
public interface CollectRepository extends JpaRepository<Collect, Long> {

    @Override
    @RestResource(exported = false)
    Optional<Collect> findById(Long id);

}
