package com.tqq.eggchat.filter;

import com.tqq.eggchat.service.SecurityService;
import com.tqq.eggchat.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 这个过滤器用于判断请求中是否有token 如果有就进行登录到spring security中
 *               继承OncePerRequestFilter 这个类是spring 对filter的封装 可以实现
 *               一次请求 只会执行一次过滤器
 * @Author: Tan
 * @CreateDate: 2019/12/6
 **/
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String tokenHead=request.getHeader("Authorization");
            //token头不等于空 并且以Bearer 开头进行token验证登录处理
            if(tokenHead!=null&&tokenHead.startsWith("Bearer ")){
                //从请求头中截取token
                String token=tokenHead.substring(7);
                //通过token得到用户名
                String userName = jwtTokenUtil.getUsernameFromToken(token);
                //用户名不等于空  并且当前上下文环境中没有认证过 就进行登录验证
                if(userName!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                    //通过用户名查询数据库 构建符合spring security要求的安全用户对象
                    UserDetails userDetails = securityService.loadUserByUsername(userName);
                    //验证token和用户对象
                    if(jwtTokenUtil.validateToken(token,userDetails)){
                        //通过安全用户对象 构建一个登录对象
                        UsernamePasswordAuthenticationToken login=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                        //传入当前http请求对象
                        login.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        //将登录对象 写入到当前上下文环境中 后续的判断 权限控制就由spring Security做
                        SecurityContextHolder.getContext().setAuthentication(login);
                    }
                }
            }
            //调用下一个过滤器  如果有token已经在此完成登录 没有登录的话 会被后续拦截器处理
            filterChain.doFilter(request,response);
    }
}
