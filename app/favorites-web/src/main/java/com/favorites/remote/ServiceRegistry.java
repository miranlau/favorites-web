/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.remote;

import com.favorites.remote.impl.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.favorites.remote.impl.BookmarkFeignService;
import com.favorites.remote.impl.FollowFeignService;

import io.favorites.common.api.ServiceFactory;

/**
 * The purpose of this class is to register remote services.
 */
@Configuration
public class ServiceRegistry {

    @Autowired
    BookmarkFeignService bookmarkFeignService;

    @Autowired
    UserFeignService userFeignService;
    
    BookmarkFeignService service;
    
    @Autowired
    FollowFeignService followFeignService;

    @Value("${favorites.services.bookmarks.address}")
    private static String bookmarksServiceAddr;
    
    @Value("${favorites.services.follow.address}")
    private static String followServiceAddr;

    /**
     * Creates a new instance of <code>ServiceRegistry</code>.
     */
    public ServiceRegistry() {
    }

    @Bean
    public BookmarkService bookmarkService() {
        return ServiceFactory.createService(BookmarkService.class, bookmarkFeignService);
    }

    @Bean
    public UserService userService() {
        return ServiceFactory.createService(UserService.class, userFeignService);
    }

    /**
     * @return Returns the bookmarksServiceAddr.
     */
    public static String getBookmarksServiceAddr() {
        return bookmarksServiceAddr;
    }
    
    
    @Bean
    public FollowService followService() {
        return ServiceFactory.createService(FollowService.class, followFeignService);
    }

    /**
     * @return Returns the bookmarksServiceAddr.
     */
    public static String getFollowServiceAddr() {
        return followServiceAddr;
    }
}
