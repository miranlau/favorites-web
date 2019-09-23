package io.favorites.follow.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.favorites.follow.domain.Follow;
import io.favorites.follow.domain.User;
import io.favorites.follow.domain.enums.FollowStatus;

@RepositoryRestResource(collectionResourceRel = "data")
public interface FollowRepository extends JpaRepository<Follow, Long> {

	@Query("select u from Follow f ,User u  where f.userId=:userId and f.followId = u.id and f.status = 'FOLLOW'")
	List<User> findByUserId(@Param("userId") Long userId);
	
	@Query("select f.followId from User u,Follow f where u.id=f.userId and f.status='FOLLOW' and u.id=:userId")
	List<Follow> findMyFollowIdByUserId(@Param("userId") Long userId);
	
	Integer countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") FollowStatus status);
	
	Integer countByFollowIdAndStatus(@Param("followId") Long followId, @Param("status") FollowStatus status);
	
//	@Query("select u.userName , u.introduction  ,u.profilePicture ,u.id  from Follow f ,User u where f.userId=:userId and f.followId = u.id and f.status = 'FOLLOW'")
	@Query("select u from Follow f ,User u where f.userId=:userId and f.followId = u.id and f.status = 'FOLLOW'")
	List<User> findFollowUserByUserId(@Param("userId") Long userId);
	
//	@Query("select u.userName , u.introduction  ,u.profilePicture ,u.id   from Follow f,User u where f.followId=:followId and f.userId = u.id and f.status='FOLLOW'")
	@Query("select u from Follow f,User u where f.followId=:followId and f.userId = u.id and f.status='FOLLOW'")
	List<User> findFollowedUserByFollowId(@Param("followId") Long followId);
	
	Integer countByUserIdAndFollowIdAndStatus(@Param("userId") Long userId, @Param("followId") Long followId, @Param("status") FollowStatus status);
	
	Follow findByUserIdAndFollowId(@Param("userId") Long userId, @Param("followId") Long followId);
	
	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update Follow set status=?1,lastModifyTime=?2 where id=?3")
	Integer updateStatusById(@Param("status") FollowStatus status, @Param("lastModifyTime") Long lastModifyTime, @Param("id") Long id);

}