package com.tqq.eggchat.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tqq.eggchat.dao.UserMapper;
import com.tqq.eggchat.entity.SecurityUser;
import com.tqq.eggchat.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Description: 这个类实现UserDetailsService接口 成为满足spring security标准的用户业务类
 *               提供根据用户名 返回 UserDetails对象的方法
 *               这里可以注入dao类对象 查询数据库 对应的用户信息 然后构造UserDetails对象
 * @Author: Tan
 * @CreateDate: 2019/12/6
 **/
@Slf4j
@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 根据用户名去数据库查询对应的用户信息
     * @Author: Tan
     * @Date: 2019/12/6
     * @param userName: 用户名
     * @return: org.springframework.security.core.userdetails.UserDetails
     **/
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("s_account", userName));
        if(user!=null){
            //这里暂时没有权限的概念 就默认个user权限
            return new SecurityUser(user.getS_account(),user.getS_password(), Arrays.asList("USER"),user.getI_status()==1?true:false);
        }else{
            log.info("查询数据库,该账号{}不存在",userName);
            throw  new UsernameNotFoundException(String.format("%s 该账号不存在",userName));
        }
    }
}
