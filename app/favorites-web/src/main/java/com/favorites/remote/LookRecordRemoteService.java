package com.favorites.remote;

import com.favorites.domain.LookRecord;
import com.favorites.domain.view.CollectView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 浏览记录service接口
 */
public interface LookRecordRemoteService {

    public Page<CollectView> findViewByUserId(Long userId, Pageable pageable);

    public Integer countByUserIdAndCollectId(Long userId, Long collectId);

    public void updatelastModifyTime(Long userId, Long collectId, Long lastModifyTime);

    public int deleteByUserIdAndCollectId(Long userId, Long collectId);

    public int deleteByUserId(Long userId);

    public LookRecord save(LookRecord lookRecord);

}
