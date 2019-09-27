package io.favorites.comment.repository;

import io.favorites.comment.domain.Comment2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;

@RepositoryRestResource(collectionResourceRel = "data", path = "comments")
public interface CommentRepository extends CrudRepository<Comment2, Long> {

    Long countByCollectId(@Param("collectId") Long collectId);

    @Override
    @RestResource(exported = true)
    @Transactional
    void deleteById(Long id);
}
