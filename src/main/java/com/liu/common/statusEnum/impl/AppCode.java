package com.liu.common.statusEnum.impl;

import com.liu.common.statusEnum.StatusCode;
import lombok.Getter;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 21:49
 * describe: 异常状态枚举
 */
@Getter
public enum AppCode implements StatusCode {
    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常"),
    ORDER_NOT_EXIST(2002,"订单异常"),
    SYSTEM_ERROR(2003,"系统异常");
    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
