package com.liu.handle;

import com.liu.common.ResultVo;
import com.liu.common.exception.ApiException;
import com.liu.common.statusEnum.impl.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 18:10
 * describe: 统一异常处理
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 绑定异常处理器
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResultVo methodArgumentNotValidExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo<>(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResultVo apiExceptionHandle(ApiException e) {
        //TODO 集成日志框架
        return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResultVo exception(Exception e) {
        System.err.println(String.format("find Exception {%s}", e));
        return new ResultVo(e.getLocalizedMessage());
    }
}
