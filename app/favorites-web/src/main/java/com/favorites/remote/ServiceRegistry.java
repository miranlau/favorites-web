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

    @Value("${favorites.services.bookmarks.address}")
    private static String bookmarksServiceAddr;

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
}
