/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.remote;

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
    BookmarkFeignService service;

    @Value("${favorites.services.bookmarks.address}")
    private static String bookmarksServiceAddr;

    /**
     * Creates a new instance of <code>ServiceRegistry</code>.
     */
    public ServiceRegistry() {
    }

    @Bean
    public BookmarkService bookmarkService() {
        return ServiceFactory.createService(BookmarkService.class, service);
    }

    /**
     * @return Returns the bookmarksServiceAddr.
     */
    public static String getBookmarksServiceAddr() {
        return bookmarksServiceAddr;
    }
}
