package com.tqq.eggchat.service;

import com.tqq.eggchat.entity.User;

/**
 * @Description: 用户相关业务类
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
public interface UserService {
    /**
     * @Description: 用户注册方法  返回注册成功的用户对象
     * @Author: Tan
     * @Date: 2019/12/8
     * @param user:要注册的用户对象
     * @return: com.tqq.eggchat.entity.User
     **/
    User userRegister (User user);
    /**
     * @Description: 用户登录方法 返回token
     * @Author: Tan
     * @Date: 2019/12/8
     * @param userName: 用户账号
     * @param passWord: 用户密码
     * @return: java.lang.String
     **/
    String userLogin(String userName,String passWord);

}
