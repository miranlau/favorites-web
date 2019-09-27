package io.favorites.repository;

import io.favorites.domain.Notice;
import io.favorites.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

@RepositoryRestResource(path = "notices", collectionResourceRel = "data")
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	@Query("select count(1) from Notice n where n.user.id=?1 and n.type=?2 and n.readed=?3")
	Long countByUserIdAndTypeAndReaded(@Param(value = "userId")Long userId, @Param(value = "type")String type, @Param(value = "readed")String readed);

	@Query("select count(1) from Notice n,Praise p where n.comment.id=p.id and n.type='praise' and n.user.id=?1 and n.readed=?2")
	Long countPraiseByUserIdAndReaded(@Param(value = "userId")Long userId, @Param(value = "readed")String readed);
	
	@Transactional
	@Modifying
	@Query("update Notice n set n.readed = ?1 where n.user.id = ?2 and n.type = ?3 and n.readed='unread'")
	int updateReadedByUserId(@Param(value = "readed")String readed, @Param(value = "userId")long userId, @Param(value = "type")String type);

}