package com.tqq.eggchat.common.enums;

/**
 * @Description: 响应code枚举类
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
public enum ResponseCodeEnum {
    /*通用HTTP状态码 */
    SUCESS(200,"成功"),

    /*用户相关状态码 10000-19999 */
    USER_LOGIN_ERROR(10000,"用户名或密码错误"),
    USER_REGISTER_ERROR(10001,"注册失败"),
    USER_TOKEN_ERROR(10002,"token 过期或错误"),
    USER_NOT_AUTHORITY(10003,"没有权限访问"),
    USER_LOGIN_SUCCESS(10004,"登录成功"),
    USER_REGISTER_SUCCESS(10005,"注册成功");


    private Integer code;
    private  String message;

    ResponseCodeEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
