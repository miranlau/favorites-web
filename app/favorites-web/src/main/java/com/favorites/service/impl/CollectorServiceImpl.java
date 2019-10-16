package com.favorites.service.impl;

import com.favorites.domain.User;
import com.favorites.domain.view.IndexCollectorView;
import com.favorites.repository.CollectorRepository;
import com.favorites.repository.UserRepository;
import com.favorites.service.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 获取收藏家
 * @Auth: yuyang
 * @Date: 2017/1/19 14:14
 * @Version: 1.0
 **/
@Service
public class CollectorServiceImpl implements CollectorService {
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CollectorRepository collectorRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * 获取收藏家
     * @return
     */
    @Override
    public IndexCollectorView getCollectors() {
        IndexCollectorView indexCollectorView = new IndexCollectorView();
        try {
            String notUserIds = "";
            Long mostCollectUser = collectorRepository.getMostCollectUser();
            if (mostCollectUser != null) {
                indexCollectorView.setMostCollectUser(userRepository.findById(mostCollectUser).orElse(null));
                notUserIds += mostCollectUser;
            }
            Long mostFollowedUser = collectorRepository.getMostFollowedUser(mostCollectUser);
            if (mostFollowedUser != null) {
                indexCollectorView.setMostFollowedUser(userRepository.findById(mostFollowedUser).orElse(null));
                notUserIds += "," + mostFollowedUser;
            }
            Long mostPraisedUser = collectorRepository.getMostPraisedUser(notUserIds);
            if (mostPraisedUser != null) {
                indexCollectorView.setMostPraisedUser(userRepository.findById(mostPraisedUser).orElse(null));
                notUserIds += "," + mostPraisedUser;
            }
            Long mostCommentedUser = collectorRepository.getMostCommentedUser(notUserIds);
            if (mostCommentedUser != null) {
                indexCollectorView.setMostCommentedUser(userRepository.findById(mostCommentedUser).orElse(null));
                notUserIds += "," + mostCommentedUser;
            }
            Long mostPopularUser = collectorRepository.getMostPopularUser(notUserIds);
            if (mostPopularUser != null) {
                indexCollectorView.setMostPopularUser(userRepository.findById(mostPopularUser).orElse(null));
                notUserIds += "," + mostPopularUser;
            }
            Long mostActiveUser = collectorRepository.getMostActiveUser(notUserIds);
            if (mostActiveUser != null) {
                indexCollectorView.setMostActiveUser(userRepository.findById(mostActiveUser).orElse(null));
            }
        }catch (Exception e){
            logger.info("错误",e);
        }
        return indexCollectorView;
    }
}
