package com.tqq.eggchat.config;

import cn.hutool.json.JSONUtil;
import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.common.enums.ResponseCodeEnum;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 用户已登录 请求没有权限的接口 响应信息
 * @Author: Tan
 * @CreateDate: 2019/12/14
 **/
@Component
public class UserNotAuthorityConfig implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //将自定义响应结果转json字符串 默认是用户名密码错误的提示信息
        String resultJsonString = JSONUtil.toJsonStr(ResponseResult.failure(ResponseCodeEnum.USER_NOT_AUTHORITY));

        //设置响应对象状态码
        httpServletResponse.setStatus(ResponseCodeEnum.USER_NOT_AUTHORITY.getCode());

        //设置响应内容类型
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //输出结果
        httpServletResponse.getWriter().print(resultJsonString);
    }
}
