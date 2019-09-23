package io.favorites.praise.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.praise.domain.Praise;
import io.favorites.praise.view.CommonUserView;

@RepositoryRestResource(collectionResourceRel = "data", path = "praise")
public interface PraiseRepository extends JpaRepository<Praise, Long> {

	Long countByCollectId(@Param("collectId") Long collectId);
	
	Praise findByUserIdAndCollectId(@Param("userId") Long userId,@Param("collectId") Long collectId);
	
	public String findPraiseUserSql = "select u.id as userId,u.userName as userName,u.profilePicture as profilePicture,"
			+ "p.createTime as createTime from Praise p,User u WHERE p.userId=u.id";
	
	@Query(findPraiseUserSql+ " and p.id=?1")
	CommonUserView findPraiseUser(@Param("id") Long id);
}
