package com.tqq.eggchat.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
public class User implements Serializable {
    /**
    * 主键 用mybatis plus 自动生成19位唯一id
    */
    private Long i_id;

    /**
    * 用户账号
    */
    private String s_account;

    /**
    * 用户密码
    */
    private String s_password;

    /**
    * 用户昵称
    */
    private String s_nike_name;

    /**
    * 用户真实姓名
    */
    private String s_name;

    /**
    * 用户性别  0 男   1女  
    */
    private Integer s_sex;

    /**
    * 用户邮箱
    */
    private String s_email;

    /**
    * 用户注册时间
    */
    private Date d_register_date;

    /**
    * 用户状态  1为正常  0为禁用
    */
    private Integer i_status;

    /**
    * 个性签名
    */
    private String s_signature;

    private static final long serialVersionUID = 1L;
}