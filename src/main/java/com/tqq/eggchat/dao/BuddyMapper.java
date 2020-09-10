package com.tqq.eggchat.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tqq.eggchat.entity.Buddy;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BuddyMapper extends BaseMapper<Buddy> {

    /**
     * 查询好友列表根据用户id
     * @Author: Tan
     * @Date: 2020/8/20
     * @param userId:  用户id
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> queryBuddyListByUserId(String userId);



}