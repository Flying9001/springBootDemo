package com.ljq.demo.springboot.web.acpect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
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
        // 获取 request 中包含的请求参数
        String uuid = UUID.randomUUID().toString();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取切点请求参数(class,method)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String params = getRequestParams(request, joinPoint);
        // 入参日志
        logger.info("[AOP-LOG-START]\n\trequestMark: {}\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                "requestMethod: {}\n\trequestParams: {}\n\ttargetClassAndMethod: {}#{}", uuid, getIpAddress(request),
                request.getHeader("Content-Type"),request.getRequestURL(), request.getMethod(), params,
                method.getDeclaringClass().getName(), method.getName());
        // 出参日志
        Object result = joinPoint.proceed();
        logger.info("[AOP-LOG-END]\n\trequestMark: {}\n\trequestUrl: {}\n\tresponse: {}",
                uuid, request.getRequestURL(), result);
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
        // 获取 request parameter 中的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && !parameterMap.isEmpty()) {
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                params.append(entry.getKey() + " = " + Arrays.toString(entry.getValue()) + ";");
            }
        }
        if (HttpMethod.POST.name().equalsIgnoreCase(request.getMethod()) ||
                HttpMethod.PUT.name().equalsIgnoreCase(request.getMethod())) {
            // 获取非 request parameter 中的参数
            Object[] objects = joinPoint.getArgs();
            for (Object arg : objects) {
                if (arg == null) {
                    break;
                }
                String className = arg.getClass().getName().toLowerCase();
                String contentType = request.getHeader("Content-Type");
                // 文件参数,上传文件信息
                if (className.contains("MultipartFile".toLowerCase())) {
                    MultipartFile multipartFile = (MultipartFile) arg;
                    params.append("fileSize = " + multipartFile.getSize() + ";");
                    params.append("fileContentType = " + multipartFile.getContentType() + ";");
                    params.append("fieldName = " + multipartFile.getName() + ";");
                    params.append("fileOriginalName = " + multipartFile.getOriginalFilename() + ";");
                }
                if (contentType != null && contentType.contains("application/json")){
                    // json 参数
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                    mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
                    params.append(mapper.writeValueAsString(arg));
                }
            }
        }
        return params.toString();
    }

    /**
     * 获取客户端请求 ip
     *
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String xip = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if(index != -1){
                return xFor.substring(0,index);
            }else{
                return xFor;
            }
        }
        xFor = xip;
        if(StringUtils.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)){
            return xFor;
        }
        if (StringUtils.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }


}
