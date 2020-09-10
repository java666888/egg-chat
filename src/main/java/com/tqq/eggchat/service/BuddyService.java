package com.tqq.eggchat.service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 好友业务接口
 * @Author: Tan
 * @CreateDate: 2020/8/20
 **/
public interface BuddyService {

    /**
     * 查询好友列表根据用户id
     * @Author: Tan
     * @Date: 2020/8/20
     * @param userId:  用户id
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String,Object>> queryBuddyListByUserId(String userId);

}
