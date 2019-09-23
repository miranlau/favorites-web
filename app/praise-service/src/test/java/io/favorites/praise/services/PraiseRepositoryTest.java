package io.favorites.praise.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.favorites.praise.domain.Praise;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PraiseRepositoryTest {
	
	@Autowired
	private PraiseRepository praise;
	
	Praise initPraise = new Praise(1l, 1l, 12414l);
	
	@Before
	public void before() {
		praise.save(initPraise);
	}
	
	@After
	public void after() {
		praise.deleteAll();
	}
	
	@Test
	public void testCountByCollectId() {
		Assert.assertEquals((Long)1l, praise.countByCollectId(1l));
	}
	
	@Test
	public void testFindByUserIdAndCollectId() {
		Assert.assertEquals(initPraise, praise.findByUserIdAndCollectId(1l, 1l));
	}
	
//	@Test
//	public void testFindPraiseUser() {
//		CommonUserView view = praise.findPraiseUser(1l);
//		Assert.assertEquals(initPraise.getUserId(), view.getUserId());
//		Assert.assertEquals(initPraise.getCreateTime(), view.getCreateTime());
//	}

}
