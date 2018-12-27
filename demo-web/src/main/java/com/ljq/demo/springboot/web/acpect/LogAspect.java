package com.ljq.demo.springboot.web.acpect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
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
        String params = getRequestParams(request, joinPoint);

        /**
         * 入参日志
         */
        logger.info("[AOP-LOG-START]\n\trequestMark: {}\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                "requestMethod: {}\n\trequestParams: {}\n\ttargetClassAndMethod: {}#{}", uuid, request.getRemoteAddr(),
                request.getHeader("Content-Type"),request.getRequestURL(), request.getMethod(), params,
                method.getDeclaringClass().getName(), method.getName());
        /**
         * 出参日志
         */
        Object result = joinPoint.proceed();
        logger.info("[AOP-LOG-END]\n\t{}", result);
        return result;
    }

    /**
     * 获取请求参数
     *
     * @param request
     * @param joinPoint
     * @return
     */
    private String getRequestParams(HttpServletRequest request, ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        StringBuilder params = new StringBuilder();
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            params.append(request.getQueryString());
        }
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            /**
             * 获取 request parameter 中的参数
             */
            Map<String,String[]> parameterMap = request.getParameterMap();
            if (parameterMap != null && !parameterMap.isEmpty()) {
                for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                    params.append(entry.getKey() + " = " + entry.getValue()[0] + ";");
                }
            }
            /**
             * 获取非 request parameter 中的参数
             */
            Object[] objects = joinPoint.getArgs();
            for (Object arg : objects) {
                String className = arg.getClass().getName().toLowerCase();
                String contentType = request.getHeader("Content-Type");
                /**
                 * 文件参数,上传文件信息
                 */
                if (className.contains("MultipartFile".toLowerCase()) || contentType.contains("multipart")) {
                    MultipartFile multipartFile = (MultipartFile) arg;
                    params.append("fileSize = " + multipartFile.getSize() + ";");
                    params.append("fileContentType = " + multipartFile.getContentType() + ";");
                    params.append("fieldName = " + multipartFile.getName() + ";");
                    params.append("fileOriginalName = " + multipartFile.getOriginalFilename() + ";");
                } else {
                    /**
                     * json 参数
                     */
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                    params.append(mapper.writeValueAsString(arg));
                }
            }
        }
        return params.toString();
    }


}
