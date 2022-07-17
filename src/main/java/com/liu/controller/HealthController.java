package com.liu.controller;

import com.liu.common.annotation.NotControllerResponseAdvice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 19:25
 * describe: 健康检查
 */
@Api(value = "健康检查",tags = "心跳检测")
@RestController
public class HealthController {

    @ApiOperation(value = "心跳检测")
    @RequestMapping(value = "/health",method = RequestMethod.GET)
    @NotControllerResponseAdvice
    public String health(){
        return "SUCCESS";
    }
}
