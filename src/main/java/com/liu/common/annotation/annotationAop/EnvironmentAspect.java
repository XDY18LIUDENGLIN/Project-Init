package com.liu.common.annotation.annotationAop;

import com.liu.common.annotation.Environment;
import com.liu.common.exception.ApiException;
import com.liu.common.statusEnum.impl.AppCode;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author : LiuDengLin
 * Email : 2092669791@qq.com
 * date : 2022/7/17 22:39
 * describe: Environment的切面
 */
@Slf4j
@Aspect
@Component
public class EnvironmentAspect {

    /***
     * 定义config切入点拦截规则，拦截Environment注解的方法
     */
    @Pointcut("@annotation(com.liu.common.annotation.Environment)")
    public void logPointCut() {
    }

    @Around(value = "logPointCut()")
    public void decideAndSetValue(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Environment environment = method.getAnnotation(Environment.class);
        Class<? extends Environment> aClass = environment.getClass();
        final Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            Environment annotation = field.getAnnotation(Environment.class);
            if (null != annotation && StringUtils.isNotBlank(annotation.value())) {
                log.info(annotation.value());
                Boolean isAppointEnvironment = annotation.value().equals(getSystemRunEnvironment());
                setFieldData(field, aClass.newInstance(), isAppointEnvironment);
                buildMethod(aClass, field);
            }
        }
    }

    private void setFieldData(Field field, Object bean, Boolean data) throws IllegalAccessException {
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (type.equals(Boolean.class)) {
            field.set(bean, data);
        } else {
            throw new ApiException(AppCode.SYSTEM_ERROR, "只支持设置在Boolean上");
        }
    }

    private String getSystemRunEnvironment() {
        return "dev";
    }

    private void buildMethod(Class<?> clz ,Field field){
        // 获取属性的名字
        String name = field.getName();
        // 将属性的首字符大写， 构造get，set方法
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        // 获取属性的类型
        String type = field.getGenericType().toString();
        log.info("属性名 -->"+name +" 类型 -->"+type);
    }
}
