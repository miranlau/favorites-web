package io.favorites.comment.repository;

import io.favorites.comment.domain.Comment;
import io.favorites.comment.domain.CommentView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "commentView", collectionResourceRel = "data", excerptProjection = CommentView.class)
public interface CommentViewRepository extends CrudRepository<Comment, Long> {

	String findReplyUserSql="SELECT c FROM Comment c WHERE c.replyUserId=?1";
	String findByCollectIdSql="SELECT c FROM Comment c WHERE c.collect.id=?1";

	@Query(findReplyUserSql)
	Comment findReplyUser(@Param("id") Long id);

	@Query(findByCollectIdSql)
	List<Comment> findByCollectIdOrderByIdDesc(@Param("collectId") Long collectId);
	
}