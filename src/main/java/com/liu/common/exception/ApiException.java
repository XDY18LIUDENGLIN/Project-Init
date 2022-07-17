package com.liu.common.exception;

import com.liu.common.statusEnum.StatusCode;
import com.liu.common.statusEnum.impl.AppCode;
import lombok.Getter;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 21:53
 * describe: XXXXXXXX
 */
@Getter
public class ApiException extends RuntimeException{
    private final int code;
    private final String msg;

    /**
     * 手动设置异常
     * @param statusCode 异常枚举类
     * @param message  错误信息
     */
    public ApiException(StatusCode statusCode,String message){
        super(message);
        this.code=statusCode.getCode();
        this.msg=statusCode.getMsg();
    }

    /**
     * 默认异常使用APP_ERROR状态码
     * @param message 错误信息
     */
    public ApiException(String message){
        super(message);
        this.code=AppCode.APP_ERROR.getCode();
        this.msg= AppCode.APP_ERROR.getMsg();
    }
}
