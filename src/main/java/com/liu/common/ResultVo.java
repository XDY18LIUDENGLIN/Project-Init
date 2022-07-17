package com.liu.common;

import com.liu.common.statusEnum.StatusCode;
import com.liu.common.statusEnum.impl.ResultCode;
import lombok.Data;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:54
 */
@Data
public class ResultVo<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 返回对象
     */
    private T data;

    /**
     * 手动设置返回vo
     */
    public ResultVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认返回成功状态码，数据对象
     * @param data
     */
    public ResultVo(T data){
        this.code= ResultCode.SUCCESS.getCode();
        this.msg=ResultCode.SUCCESS.getMsg();
        this.data=data;
    }

    /**
     * 返回指定状态码，数据对象
     * @param statusCode
     * @param data
     */
    public ResultVo(StatusCode statusCode,T data){
        this.code=statusCode.getCode();
        this.msg=statusCode.getMsg();
        this.data=data;
    }

    /**
     * 只返回状态码
     * @param statusCode
     */
    public ResultVo(StatusCode statusCode){
        this.code=statusCode.getCode();
        this.msg= statusCode.getMsg();
        this.data=null;
    }
}
