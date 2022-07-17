package com.liu.common.statusEnum.impl;

import com.liu.common.statusEnum.StatusCode;
import lombok.Getter;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:46
 */
@Getter
public enum ResultCode implements StatusCode {
    SUCCESS(1000, "请求成功"),
    FAILED(1001, "请求失败"),
    VALIDATE_ERROR(1002, "参数校验失败"),
    RESPONSE_PACK_ERROR(1003, "response返回包装失败");

    /**
     * 状态码
     */
    private int code;

    /**
     * 状态消息
     */
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
