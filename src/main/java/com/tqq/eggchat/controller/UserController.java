package com.tqq.eggchat.controller;

import cn.hutool.core.util.StrUtil;
import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.common.enums.ResponseCodeEnum;
import com.tqq.eggchat.entity.User;
import com.tqq.eggchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 用户控制器
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/userLogin")
    public ResponseResult userLogin(User user){
        String token = userService.userLogin(user.getS_account(),user.getS_password());
        return StrUtil.isNotBlank(token)?ResponseResult.success(token):ResponseResult.failure(ResponseCodeEnum.USER_LOGIN_ERROR);
    }

    @PostMapping("/userRegister")
    public ResponseResult userRegister(User user){
        return userService.userRegister(user)!=null?ResponseResult.success():ResponseResult.failure(ResponseCodeEnum.USER_REGISTER_ERROR);
    }


}
