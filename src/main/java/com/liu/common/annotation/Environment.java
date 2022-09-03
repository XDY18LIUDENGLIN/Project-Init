package com.liu.common.annotation;

import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 22:21
 * describe: 设置只在传入参数所指定的环境下为true
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Environment {
    /**
     * 传入环境参数
     * @return
     */
    @NotNull(message = "环境参数不允许为空")
    String value() ;
}
