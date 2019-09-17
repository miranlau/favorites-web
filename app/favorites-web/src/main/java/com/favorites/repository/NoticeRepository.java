package com.favorites.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.favorites.domain.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
	
	Long countByUserIdAndTypeAndReaded(Long userId,String type,String readed);
	
	@Query("select count(1) from Notice n,Praise p where n.operId=p.id and type='praise' and n.userId=?1 and n.readed=?2")
	Long countPraiseByUserIdAndReaded(Long userId,String readed);
	
	@Transactional
	@Modifying
	@Query("update Notice n set n.readed = ?1 where n.userId = ?2 and n.type = ?3 and n.readed='unread'")
	int updateReadedByUserId(String readed, long userId, String type);

}