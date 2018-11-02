package com.ljq.demo.springboot.web.acpect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @Description: 日志记录切点
 * @Author: junqiang.lu
 * @Date: 2018/11/1
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * controller 层切点
     */
    @Pointcut("execution(* com.ljq.demo.springboot.web.controller..*.*(..))")
    public void controllerPointcut() {
    }

    /**
     * controller 层出入参日志记录
     *
     * @param joinPoint 切点
     * @return
     */
    @Around(value = "controllerPointcut()")
    public Object controllerLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 获取 request 中包含的请求参数
         */
        String uuid = UUID.randomUUID().toString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /**
         * 获取切点请求参数(class,method)
         */
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        StringBuilder params = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Object[] objects = joinPoint.getArgs();
            for (Object arg : objects) {
                params.append(mapper.writeValueAsString(arg));
            }
        }
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            params.append(request.getQueryString());
        }
        /**
         * 入参日志
         */
        logger.info("[AOP-LOG-START]\n\trequestMark: {}\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                "requestMethod: {}\n\trequestParams: {}\n\ttargetClassAndMethod: {}#{}", uuid, request.getRemoteAddr(),
                request.getHeader("Content-Type"),request.getRequestURL(), request.getMethod(), params.toString(),
                method.getDeclaringClass().getName(), method.getName());
        /**
         * 出参日志
         */
        Object result = joinPoint.proceed();
        logger.info("[AOP-LOG-END]\n\t{}", result);
        return result;
    }


}
