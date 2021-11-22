package com.ljq.demo.springboot.web.acpect;

import com.ljq.demo.springboot.baseweb.log.LogService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description: 简易拦截器应用
 * @Author: junqiang.lu
 * @Date: 2019/12/10
 */
@Slf4j
@Component
public class SimpleInterceptor implements HandlerInterceptor {

    @Autowired
    private LogService logService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("requestId", UUID.randomUUID().toString());
        log.info("preHandle");
        if (Objects.nonNull(request.getQueryString())) {
            logService.logRequest(request, null);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");

    }
}
