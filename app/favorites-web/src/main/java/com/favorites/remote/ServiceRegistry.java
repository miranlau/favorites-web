/*------------------------------------------------------------------------------
 * @author ahanqiankun@aliyun.com
 *----------------------------------------------------------------------------*/
package com.favorites.remote;

import com.favorites.remote.impl.*;

import io.favorites.common.api.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The purpose of this class is to register remote services.
 */
@Configuration
public class ServiceRegistry {

    @Autowired
    BookmarkFeignService bookmarkFeignService;

    @Autowired
    UserFeignService userFeignService;
    
    @Autowired
    PraiseFeignService praiseFeignService;
    
    @Autowired
    FollowFeignService followFeignService;

    @Autowired
    LookRecordFeignService lookRecordFeignService;

    @Autowired
    FolderFeignService folderFeignService;

    @Autowired
    CommentFeignService commentFeignService;

    @Value("${favorites.services.bookmarks.address}")
    private static String bookmarksServiceAddr;
    
    @Value("${favorites.services.follow.address}")
    private static String followServiceAddr;

    @Value("${favorites.services.lookrecord.address}")
    private static String lookRecordServiceAddr;
    
    @Value("${favorites.services.praise.address}")
    private static String praiseServiceAddr;

    @Value("${favorites.services.comment.address}")
    private static String commentServiceAddr;

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
    
    @Bean
    public PraiseService praiseService() {
        return ServiceFactory.createService(PraiseService.class, praiseFeignService);
    }

    @Bean
    public LookRecordRemoteService lookRecordRemoteService() {
        return ServiceFactory.createService(LookRecordRemoteService.class, lookRecordFeignService);
    }
    
    @Bean
    public FollowService followService() {
        return ServiceFactory.createService(FollowService.class, followFeignService);
    }

    @Bean
    public FolderService folderService() {
        return ServiceFactory.createService(FolderService.class, folderFeignService);
    }

    @Bean
    public CommentService commentService() {
        return ServiceFactory.createService(CommentService.class, commentFeignService);
    }


    /**
     * @return Returns the bookmarksServiceAddr.
     */
    public static String getBookmarksServiceAddr() {
        return bookmarksServiceAddr;
    }
    
    /**
     * @return Returns the praiseServiceAddr.
     */
    public static String getPraiseServiceAddr() {
        return praiseServiceAddr;
    }

    /**
     * @return Returns the bookmarksServiceAddr.
     */
    public static String getFollowServiceAddr() {
        return followServiceAddr;
    }

    /**
     * @return Returns the lookRecordServiceAddr.
     */
    public static String getLookRecordServiceAddr() {
        return lookRecordServiceAddr;
    }

    public static String getCommentServiceAddr() {
        return commentServiceAddr;
    }


}
