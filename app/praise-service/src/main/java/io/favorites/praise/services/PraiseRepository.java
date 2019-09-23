package io.favorites.praise.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.praise.domain.Praise;

@RepositoryRestResource(collectionResourceRel = "praise", path = "praise")
public interface PraiseRepository extends JpaRepository<Praise, Long> {

}
