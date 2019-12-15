package com.tqq.eggchat.config;

import com.tqq.eggchat.filter.JwtRequestFilter;
import com.tqq.eggchat.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: spring security核心配置类 功能都在此配置
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

     @Autowired
     private SecurityService securityService;

     @Autowired
     private JwtRequestFilter jwtRequestFilter;

     @Autowired
     private UserLoginFailurceConfig userLoginFailurceConfig;

     @Autowired
     private UserNotAuthorityConfig userNotAuthorityConfig;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //配置密码的加密方式
        auth.userDetailsService(securityService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //向spring容器中注入 认证管理器
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
         //关闭csrf防护器
        http.csrf().disable()
                 //session管理器为无状态
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //登录和注册所有人可以访问
                .authorizeRequests().antMatchers("/user/userLogin","/user/userRegister","/user/demo").permitAll()
                //放行swagger2
                .antMatchers("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**").permitAll()
                //所有请求需要认证
                .anyRequest().authenticated()
                .and()
                //添加我们实现的过滤器到spring security过滤器链的第一个 进行过滤
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        //禁用缓存
        http.headers().cacheControl();

        //设置异常处理  authenticationEntryPoint是身份验证失败的处理,accessDeniedHandler是没有权限访问处理
        http.exceptionHandling().authenticationEntryPoint(userLoginFailurceConfig).accessDeniedHandler(userNotAuthorityConfig);
    }


}
