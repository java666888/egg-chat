package com.tqq.eggchat.controller;

import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.service.BuddyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: Tan
 * @CreateDate: 2020/8/23
 **/
@RestController
@RequestMapping("/buddy")
@Slf4j
public class BuddyController {

    @Autowired
    private BuddyService buddyService;


    @GetMapping("/list/{userAccount}")
    public ResponseResult buddylist(@PathVariable("userAccount") String userAccount){
        ResponseResult result=null;
        try {
           result=  ResponseResult.success("data",buddyService.queryBuddyListByUserId(userAccount));
        }catch (Exception e){
            log.error("查询好友列表错误",e);
        }finally {
           return result;
        }
    }


}
