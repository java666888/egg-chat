package com.tqq.eggchat.filter;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.tqq.eggchat.service.SecurityService;
import com.tqq.eggchat.util.JwtTokenUtil;
import com.tqq.eggchat.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.Date;

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

    @Autowired
    private RedisUtil redisUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String tokenHead=request.getHeader("Authorization");
            //token头不等于空 并且以Bearer 开头进行token验证登录处理
            if(tokenHead!=null&&tokenHead.startsWith("Bearer ")){
                //从请求头中截取token
                String token=tokenHead.substring(7);
                //看看缓存中是否有该token为key的值 如果有 代表上一个token即将或已经过期 在缓存中存放了新的token
                Object caChing = redisUtil.get(token);
                if(caChing!=null){
                    //从缓存中 取出新的token  然后进行登录
                    String caChingToken=(String)caChing;
                    loginSpringSecurity(caChingToken,request,token);
                }else{
                    //缓存中 没有token 可以理解尚未过期,或者token已过期 直接验证
                    loginSpringSecurity(token,request,token);
                }
            }
            //调用下一个过滤器  如果有token已经在此完成登录 没有登录的话 会被后续拦截器处理
            filterChain.doFilter(request,response);
    }


    public void loginSpringSecurity(String token,HttpServletRequest request,String caChingkey){
        //通过token得到用户名  如果token已过期或者错误 会抛出异常,并被spring security捕获 调我们自定义的登录失败处理方法
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
                //计算过期时间,如果临近过期 以客户端请求的token为key 重新生成新的token 存入redis缓存 实现自动更新缓存
                long ExpirationDate = DateUtil.between(jwtTokenUtil.getExpirationDateFromToken(token),new Date(), DateUnit.SECOND);
                if(ExpirationDate<300){
                    redisUtil.set(caChingkey,jwtTokenUtil.generateToken(userDetails),600);
                }
            }
        }
    }



}
