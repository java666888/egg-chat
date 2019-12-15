package com.tqq.eggchat.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tqq.eggchat.dao.UserMapper;
import com.tqq.eggchat.entity.User;
import com.tqq.eggchat.service.SecurityService;
import com.tqq.eggchat.service.UserService;
import com.tqq.eggchat.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityService securityService;


    @Override
    public User userRegister(User user) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        user.setS_password(passwordEncoder.encode(user.getS_password()));
        userMapper.insert(user);
        return user;
    }

    @Override
    public String userLogin(String userName, String passWord) {
        //根据输入的用户名和密码创建一个 用户名密码token
        UsernamePasswordAuthenticationToken userNamepassWordToken = new UsernamePasswordAuthenticationToken( userName, passWord );
        //创建认证对象 传入用户名密码token 然后spring security会根据用户名去调用loadUserByUsername方法
        //这个方法是由我们重写了 是根据用户名去数据库查询 只要查询到记录 交由spring security进行密码比较
        //如果没有查询到对应记录 或者密码不正确 就会直接响应403  这里后续代码不会执行
        Authentication authentication = authenticationManager.authenticate(userNamepassWordToken);
        //执行到这一步 代表用户名密码已经校验成功了 将认证对象写入到spring security上下文环境中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //根据用户名查询结果 然后生成token 并返回
        UserDetails userDetails = securityService.loadUserByUsername( userName );
        return jwtTokenUtil.generateToken(userDetails);
    }
}
