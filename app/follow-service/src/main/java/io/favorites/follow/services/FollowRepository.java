package io.favorites.follow.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.favorites.follow.domain.Follow;
import io.favorites.follow.domain.enums.FollowStatus;

public interface FollowRepository extends JpaRepository<Follow, Long> {

	@Query("select u.userName from Follow f ,User u  where f.userId=:userId and f.followId = u.id and f.status = 'FOLLOW'")
	List<String> findByUserId(@Param("userId") Long userId);
	
	@Query("select f.followId from User u,Follow f where u.id=f.userId and f.status='FOLLOW' and u.id=:userId")
	List<Long> findMyFollowIdByUserId(@Param("userId") Long userId);
	
	Integer countByUserIdAndStatus(@Param("userId") Long userId, @Param("status") FollowStatus status);
	
	Integer countByFollowIdAndStatus(@Param("followId") Long followId, @Param("status") FollowStatus status);
	
	@Query("select u.userName , u.introduction  ,u.profilePicture ,u.id  from Follow f ,User u where f.userId=:userId and f.followId = u.id and f.status = 'FOLLOW'")
	List<String> findFollowUserByUserId(@Param("userId") Long userId);
	
	@Query("select u.userName , u.introduction  ,u.profilePicture ,u.id   from Follow f,User u where f.followId=:followId and f.userId = u.id and f.status='FOLLOW'")
	List<String> findFollowedUserByFollowId(@Param("followId") Long followId);
	
	Integer countByUserIdAndFollowIdAndStatus(@Param("userId") Long userId, @Param("followId") Long followId, @Param("status") FollowStatus status);
	
	Follow findByUserIdAndFollowId(@Param("userId") Long userId, @Param("followId") Long followId);
	
	@Modifying(clearAutomatically=true)
	@Transactional
	@Query("update Follow set status=?1,lastModifyTime=?2 where id=?3")
	Integer updateStatusById(@Param("status") FollowStatus status, @Param("lastModifyTime") Long lastModifyTime, @Param("id") Long id);

}