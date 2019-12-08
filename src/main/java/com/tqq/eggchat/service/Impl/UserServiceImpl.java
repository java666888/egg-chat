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
        //根据用户名查询用户信息
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("s_account", userName));
        if(user!=null){
            BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
           String  tempPassWord=passwordEncoder.encode(passWord);
            if(userName.equals(user.getS_account())&&tempPassWord.equals(user.getS_password())){
                UsernamePasswordAuthenticationToken login=new UsernamePasswordAuthenticationToken(userName,passWord);
                Authentication authenticate = authenticationManager.authenticate(login);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                return jwtTokenUtil.generateToken(securityService.loadUserByUsername(userName));
            }
        }
        return null;
    }
}
