package io.favorites.service.impl;

import io.favorites.domain.*;
import io.favorites.repository.LookRecordRepository;
import io.favorites.service.LookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookRecordServiceImpl implements LookRecordService {

    @Autowired
    private LookRecordRepository lookRecordRepository;

    @Override
    public LookRecordBiz saveLookRecord(LookRecordBiz lookRecordBiz) {
        LookRecord lookRecord = new LookRecord();
        Collect collect = new Collect();
        collect.setId(lookRecordBiz.getCollectId());
        lookRecord.setCollect(collect);
        lookRecord.setCreateTime(lookRecordBiz.getCreateTime());
        lookRecord.setLastModifyTime(lookRecordBiz.getLastModifyTime());
        User user = new User();
        user.setId(lookRecordBiz.getUserId());
        lookRecord.setUser(user);
        LookRecord result = lookRecordRepository.save(lookRecord);
        LookRecordBiz recordBiz = new LookRecordBiz();
        recordBiz.setId(result.getId());
        recordBiz.setCollectId(result.getCollect().getId());
        recordBiz.setCreateTime(result.getCreateTime());
        recordBiz.setLastModifyTime(result.getLastModifyTime());
        recordBiz.setUserId(result.getUser().getId());
        return recordBiz;
    }
}
