package com.favorites.service.impl;

import com.favorites.domain.LookRecord;
import com.favorites.domain.Praise;
import com.favorites.domain.view.CollectSummary;
import com.favorites.domain.view.CollectView;
import com.favorites.remote.LookRecordRemoteService;
import com.favorites.remote.impl.LookRecordFeignService;
import com.favorites.repository.CommentRepository;
import com.favorites.repository.PraiseRepository;
import com.favorites.service.CollectService;
import com.favorites.service.LookRecordService;
import com.favorites.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("lookRecordService")
public class LookRecordServiceImpl implements LookRecordService {

    @Autowired
    private LookRecordRemoteService lookRecordRemoteService;

    @Autowired
    private LookRecordFeignService lookRecordFeignService;

    @Autowired
    private PraiseRepository praiseRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CollectService collectService;

    @Override
    public void saveLookRecord(Long userId,Long collectId) {
        if(userId != null && userId > 0 && collectId != null) {
            Integer count = lookRecordFeignService.countByUserIdAndCollectId(userId, collectId);
            if (count > 0) {
                lookRecordFeignService.updatelastModifyTime(userId, collectId, DateUtils.getCurrentTime());
            } else {
                LookRecord lookRecord = new LookRecord();
                lookRecord.setUserId(userId);
                lookRecord.setCollectId(collectId);
                lookRecord.setCreateTime(DateUtils.getCurrentTime());
                lookRecord.setLastModifyTime(DateUtils.getCurrentTime());
                lookRecordFeignService.save(lookRecord);
            }
        }

    }

    @Override
    public void deleteLookRecord(Long userId, Long collectId) {
        lookRecordFeignService.deleteByUserIdAndCollectId(userId,collectId);
    }

    @Override
    public void deleteLookRecordByUserID(Long userId) {
        lookRecordFeignService.deleteByUserId(userId);
    }

    @Override
    public List<CollectSummary> getLookRecords(Long userId, Pageable pageable) {
        Page<CollectView> views = null;

        views = lookRecordRemoteService.findViewByUserId(userId, pageable);

        return convertCollect(views,userId);
    }

    /**
     * @author neo
     * @date 2016年8月11日
     * @return
     */
    private List<CollectSummary> convertCollect(Page<CollectView> views,Long userId) {
        List<CollectSummary> summarys=new ArrayList<CollectSummary>();
        for (CollectView view : views) {
            CollectSummary summary=new CollectSummary(view);
            summary.setPraiseCount(praiseRepository.countByCollectId(view.getId()));
            summary.setCommentCount(commentRepository.countByCollectId(view.getId()));
            Praise praise=praiseRepository.findByUserIdAndCollectId(userId, view.getId());
            if(praise!=null){
                summary.setPraise(true);
            }else{
                summary.setPraise(false);
            }
            summary.setCollectTime(DateUtils.getTimeFormatText(view.getCreateTime()));
            summarys.add(summary);
        }
        return summarys;
    }


}
