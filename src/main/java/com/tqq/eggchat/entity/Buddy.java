package com.tqq.eggchat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_buddy")
public class Buddy implements Serializable {
    /**
     * 主键 用mybatis plus 自动生成19位唯一id
     */
    @TableId(value = "i_id", type = IdType.INPUT)
    private Long i_id;

    /**
     * 用户账号
     */
    @TableField(value = "s_user_account")
    private String s_user_account;

    /**
     * 好友id
     */
    @TableField(value = "s_buddy_account")
    private String s_buddy_account;

    /**
     * 好友备注
     */
    @TableField(value = "s_remarks")
    private String s_remarks;

    /**
     * 创建好友关系时间
     */
    @TableField(value = "d_create_time")
    private Date d_create_time;

    private static final long serialVersionUID = 1L;
}