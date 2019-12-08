package com.tqq.eggchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@TableName(value = "t_user")
public class User implements Serializable {
    /**
     * 主键 用mybatis plus 自动生成19位唯一id
     */
     @TableId(value = "i_id", type = IdType.ID_WORKER)
    private Long i_id;

    /**
     * 用户账号
     */
    @TableField(value = "s_account")
    private String s_account;

    /**
     * 用户密码
     */
    @TableField(value = "s_password")
    private String s_password;

    /**
     * 用户昵称
     */
    @TableField(value = "s_nike_name")
    private String s_nike_name;

    /**
     * 用户真实姓名
     */
    @TableField(value = "s_name")
    private String s_name;

    /**
     * 用户性别  0 男   1女  
     */
    @TableField(value = "s_sex")
    private Integer s_sex;

    /**
     * 用户邮箱
     */
    @TableField(value = "s_email")
    private String s_email;

    /**
     * 用户注册时间
     */
    @TableField(value = "d_register_date")
    private Date d_register_date;

    /**
     * 用户状态  1为正常  0为禁用
     */
    @TableField(value = "i_status")
    private Integer i_status;

    /**
     * 个性签名
     */
    @TableField(value = "s_signature")
    private String s_signature;

    private static final long serialVersionUID = 1L;
}