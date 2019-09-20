package io.favorites.controller;

import io.favorites.domain.LookRecordBiz;
import io.favorites.domain.result.ExceptionMsg;
import io.favorites.domain.result.Response;
import io.favorites.domain.result.ResponseData;
import io.favorites.service.LookRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lookRecords")
public class LookRecordController extends BaseController{

    @Autowired
    private LookRecordService lookRecordService;

    @PostMapping(value = "/save")
    public Response createLookRecord(@RequestBody LookRecordBiz lookRecordBiz){
        LookRecordBiz newLookRecord = lookRecordService.saveLookRecord(lookRecordBiz);
        return new ResponseData(ExceptionMsg.SUCCESS, newLookRecord);
    }
}
