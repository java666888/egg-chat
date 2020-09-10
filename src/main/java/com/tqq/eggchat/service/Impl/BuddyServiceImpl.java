package com.tqq.eggchat.service.Impl;

import com.tqq.eggchat.dao.BuddyMapper;
import com.tqq.eggchat.service.BuddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description: 好友业务实现类
 * @Author: Tan
 * @CreateDate: 2020/8/20
 **/
@Service
public class BuddyServiceImpl implements BuddyService {

    @Autowired
    private BuddyMapper buddyMapper;

    /**
     * 查询好友列表根据用户id
     *
     * @param userId :  用户id
     * @Author: Tan
     * @Date: 2020/8/20
     * @return: java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> queryBuddyListByUserId(String userId) {
            return    buddyMapper.queryBuddyListByUserId(userId);
    }


}
