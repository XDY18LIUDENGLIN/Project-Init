package com.liu.common.annotation.annotationAop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 22:39
 * describe: Environment的切面
 */
//TODO 自定义注解注入值
@Slf4j
@Aspect
@Component
public class EnvironmentAspect {

    /***
     * 定义controller切入点拦截规则，拦截Environment注解的方法
     */
    @Pointcut("@annotation(com.liu.common.annotation.Environment)")
    public void environment() {
    }

    @Around(value = "environment()")
    public void decideAndSetValue(ProceedingJoinPoint joinPoint) {
        log.info(" TEST--> "+joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Class<?> entity = args[0].getClass();
        try {
            Field[] fields = entity.getDeclaredFields();
            for (Field field : fields) {
                log.error(field.getName());
            }
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
