package com.tqq.eggchat.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 实现 UserDetails 重写方法 就是满足spring security安全要求的用户
 *               spring security验证用户必须要使用实现UserDetails的类,的实例
 *               所以构建这个类 将我们自身实体类中的一些字段 赋值到这个类 用于校验
 *               也是由于我们自身的用户实体类 字段比较多
 * @Author: Tan
 * @CreateDate: 2019/12/6
 **/
public class SecurityUser implements UserDetails {
    //用户名
    private String userName;
    //密码
    private String passWord;
    //权限集合
    private  Collection<? extends GrantedAuthority> authoritys;
    //是否可用
    private  boolean enabled;

    /**
     * @Description:  这个构造方法  从用户实体对象中给这个安全用户赋值
     * @Author: Tan
     * @Date: 2019/12/6
     * @param userName: 用户账号
     * @param passWord:  用户密码
     * @param authority:  用户权限集合
     * @param enabled:  用户是否可用
     * @return: null
     **/
    public SecurityUser (String userName, String passWord, List<String> authority,boolean enabled ){
        this.userName=userName;
        this.passWord=passWord;
        this.enabled=enabled;
        this.authoritys =authority.stream().map(item->new SimpleGrantedAuthority(item)).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authoritys;
    }

    @Override
    public String getPassword() {
        return this.passWord;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
