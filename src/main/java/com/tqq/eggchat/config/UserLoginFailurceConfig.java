package com.tqq.eggchat.config;

import cn.hutool.json.JSONUtil;
import com.tqq.eggchat.common.ResponseResult;
import com.tqq.eggchat.common.enums.ResponseCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 当spring security验证用户身份失败时 会调用 这个类的commence方法
 *               有两种情况 第一张用户名密码错误  第二种 就是token过期或者错误
 * @Author: Tan
 * @CreateDate: 2019/12/14
 **/
@Component
public class UserLoginFailurceConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        //将自定义响应结果转json字符串 默认是用户名密码错误的提示信息
        String resultJsonString = JSONUtil.toJsonStr(ResponseResult.failure(ResponseCodeEnum.USER_LOGIN_ERROR));

        //设置响应对象状态码
        httpServletResponse.setStatus(ResponseCodeEnum.USER_LOGIN_ERROR.getCode());

        //从请求对象中获取token
        String tokenHead = httpServletRequest.getHeader("Authorization");
        //如果token不是空  并且Bearer 开头 那么就是token错误或者过期了
        if(tokenHead!=null&&tokenHead.startsWith("Bearer ")){
            //将token错误或过期提示 转换成字符串
             resultJsonString = JSONUtil.toJsonStr(ResponseResult.failure(ResponseCodeEnum.USER_TOKEN_ERROR));
            //设置响应对象状态码
            httpServletResponse.setStatus(ResponseCodeEnum.USER_TOKEN_ERROR.getCode());
        }

        //设置响应内容类型
        httpServletResponse.setContentType("application/json;charset=utf-8");
        //输出结果
        httpServletResponse.getWriter().print(resultJsonString);
    }
}
