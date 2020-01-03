package com.tqq.eggchat.controller;

import cn.hutool.core.util.StrUtil;
import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.common.enums.ResponseCodeEnum;
import com.tqq.eggchat.entity.User;
import com.tqq.eggchat.service.UserService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("用户登录")
    @GetMapping("/userLogin")
    public ResponseResult userLogin(User user){
        String token = userService.userLogin(user.getS_account(),user.getS_password());
        return StrUtil.isNotBlank(token)?ResponseResult.success(ResponseCodeEnum.USER_LOGIN_SUCCESS,"token",token):ResponseResult.failure(ResponseCodeEnum.USER_LOGIN_ERROR);
    }

    @ApiOperation("用户注册")
    @PostMapping("/userRegister")
    public ResponseResult userRegister(@RequestBody User user){
        return userService.userRegister(user)!=null?ResponseResult.success(ResponseCodeEnum.USER_REGISTER_SUCCESS):ResponseResult.failure(ResponseCodeEnum.USER_REGISTER_ERROR);
    }

    @ApiOperation("检查用户名是否可用")
    @GetMapping("/checkUserAccount")
    public ResponseResult checkUserAccount(String account){
        return ResponseResult.success("checkUserAccount",userService.checkUserAccount(account));
    }

    @GetMapping("/test")
    public ResponseResult test(){
        return ResponseResult.success();
    }


}
