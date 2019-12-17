package com.tqq.eggchat.controller;

import cn.hutool.core.util.StrUtil;
import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.common.enums.ResponseCodeEnum;
import com.tqq.eggchat.entity.User;
import com.tqq.eggchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PostMapping("/userLogin")
    public ResponseResult userLogin(User user){
        String token = userService.userLogin(user.getS_account(),user.getS_password());
        return StrUtil.isNotBlank(token)?ResponseResult.success(ResponseCodeEnum.USER_LOGIN_SUCCESS,"token",token):ResponseResult.failure(ResponseCodeEnum.USER_LOGIN_ERROR);
    }

    @PostMapping("/userRegister")
    public ResponseResult userRegister(User user){
        return userService.userRegister(user)!=null?ResponseResult.success(ResponseCodeEnum.USER_REGISTER_SUCCESS):ResponseResult.failure(ResponseCodeEnum.USER_REGISTER_ERROR);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/test")
    public ResponseResult test(){
        return ResponseResult.success("你拥有用户权限");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin")
    public ResponseResult admin(){
        return ResponseResult.success("你拥有管理员权限");
    }



}
