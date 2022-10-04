package com.liu.rest.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/10/4 21:28
 * describe: XXXXXXXX
 */
@RestController
public class Test {

    @Value("${zookeeper.discover.address}")
    private String host;

    @RequestMapping("test")
    public String test(){
        return "Hello world "+host;
    }
}
