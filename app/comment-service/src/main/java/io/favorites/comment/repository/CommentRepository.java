package io.favorites.comment.repository;

import io.favorites.comment.domain.Comment2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "data", path = "comments")
public interface CommentRepository extends CrudRepository<Comment2, Long> {

    Long countByCollectId(@Param("collectId") Long collectId);

    List<Comment2> findByCollectIdOrderByIdDesc(@Param("collectId") Long collectId);

    @Transactional
    void deleteById(@Param("id") Long id);
}
