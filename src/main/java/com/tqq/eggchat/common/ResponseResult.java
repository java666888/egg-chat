package com.tqq.eggchat.common;

import com.tqq.eggchat.common.enums.ResponseCodeEnum;

/**
 * @Description: 统一封装接口返回数据的格式
 * @Author: Tan
 * @CreateDate: 2019/12/8
 **/
public class ResponseResult {

     private Integer code;
     private String message;
     private Object data;


     public static  ResponseResult success(){
         ResponseResult result=new ResponseResult();
         result.setCodeAndMessage(ResponseCodeEnum.SUCESS);
         return result;
     }

    public static  ResponseResult success(Object data){
        ResponseResult result=new ResponseResult();
        result.setCodeAndMessage(ResponseCodeEnum.SUCESS);
        result.data=data;
        return result;
    }

    public static  ResponseResult failure(ResponseCodeEnum responseCodeEnum){
        ResponseResult result=new ResponseResult();
        result.setCodeAndMessage(responseCodeEnum);
        return result;
    }

    public static  ResponseResult failure(ResponseCodeEnum responseCodeEnum,Object data){
        ResponseResult result=new ResponseResult();
        result.setCodeAndMessage(responseCodeEnum);
        result.data=data;
        return result;
    }

     public void setCodeAndMessage(ResponseCodeEnum responseCodeEnum){
         this.code=responseCodeEnum.getCode();
         this.message=responseCodeEnum.getMessage();
     }


    public ResponseResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(){

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
