package com.liu.common.statusEnum.impl;

import com.liu.common.statusEnum.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 14:46
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements StatusCode {

    /**
     * 通用类型返回接口
     */
    SUCCESS(200,"请求成功"),
    FAILED(201,"请求失败"),
    VALIDATE_ERROR(1002,"参数校验失败"),
    RESPONSE_PACK_ERROR(1003,"response返回包装失败");


    /**
     * 图片类特定返回接口
     */


    /**
     * 视频类特定返回接口
     */














    /**
     * 状态码
     */
    private int code;
    /**
     * 状态消息
     */
    private String msg;
}
