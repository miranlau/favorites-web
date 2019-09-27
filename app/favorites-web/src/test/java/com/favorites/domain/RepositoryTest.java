package com.favorites.domain;

import com.favorites.remote.PraiseService;
import com.favorites.remote.CommentService;
import com.favorites.repository.FollowRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

	@Autowired
	private PraiseService praiseRepository;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private FollowRepository followRepository;

	@Test
	public void testPraise() throws Exception {
		long count=praiseRepository.countByCollectId(1l);
		System.out.println("count===="+count);
		Praise praise=praiseRepository.findByUserIdAndCollectId(1l, 1l);
		System.out.println("exists===="+praise);

	}
	
	
	@Test
	public void testComment() throws Exception {
		long count= commentService.countByCollectId(1l);
		System.out.println("count===="+count);
	    
	}
	
	
	@Test
	public void testFollow() throws Exception {
		List<Long> userIds=followRepository.findMyFollowIdByUserId(1l);
		for(Long userId:userIds){
			System.out.println("userId===="+userId);
		}
	    
	}
	

}