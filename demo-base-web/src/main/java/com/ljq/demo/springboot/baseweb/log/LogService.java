package com.ljq.demo.springboot.baseweb.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 日志记录工具类
 * @Author: junqiang.lu
 * @Date: 2020/1/8
 */
@Component
@Slf4j
public class LogService {


    /**
     * 记录请求参数日志
     *
     * @param request
     * @param body
     */
    public void logRequest(HttpServletRequest request, Object body) {
        log.info("[LOG-REQUEST]\n\trequestIP: {}\n\tcontentType:{}\n\trequestUrl: {}\n\t" +
                        "requestMethod: {}\n\trequestParams: {}\n\trequestBody: {}",
                getIpAddress(request),request.getHeader("Content-Type"),
                request.getRequestURL(), request.getMethod(), getRequestParamsMap(request), body);
    }

    /**
     * 记录返回参数日志
     *
     * @param request
     * @param response
     * @param body
     */
    public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
        log.info("[LOG-RESPONSE]\n\trequestIp: {}\n\trequestUrl: {}\n\tresponse: {}",
                getIpAddress(request), request.getRequestURL(), body);
    }

    /**
     * 获取请求参数
     *
     * @param httpServletRequest
     * @return
     */
    private Map<String, String> getRequestParamsMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>(16);
        if (httpServletRequest.getParameterMap() != null && !httpServletRequest.getParameterMap().isEmpty()) {
            for (Map.Entry<String, String[]> entry : httpServletRequest.getParameterMap().entrySet()) {
                resultMap.put(entry.getKey(),Arrays.toString(entry.getValue()));
            }
        }
        return resultMap;
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
