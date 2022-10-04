package com.liu;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/16 15:32
 */
@SpringBootApplication
@EnableDubbo
public class ProjectInitMain {
    public static void main(String[] args) {
        SpringApplication.run(ProjectInitMain.class,args);
    }
}
