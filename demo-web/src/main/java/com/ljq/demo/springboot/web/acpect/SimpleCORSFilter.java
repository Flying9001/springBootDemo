package com.ljq.demo.springboot.web.acpect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ResponseCode;
import com.ljq.demo.springboot.common.constant.TokenConst;
import com.ljq.demo.springboot.common.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @Description: 跨域请求拦截器(简易版)
 * @Author: junqiang.lu
 * @Date: 2019/5/21
 */
@Slf4j
public class SimpleCORSFilter implements Filter {

    /**
     * 不需要 Token 校验的接口
     */
    private final static String[] NO_TOKEN_API_PATHS ={
            "/**/favicon.ico",
            "/swagger-ui.html",
            "/webjars/**",
            "/webjars/springfox-swagger-ui/springfox.css",
            "/webjars/springfox-swagger-ui/springfox.js",
            "/webjars/springfox-swagger-ui/swagger-ui-bundle.js",
            "/webjars/springfox-swagger-ui/swagger-ui.css",
            "/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-resources",
            "/v2/api-docs",
            "/",
            "/csrf",
            "/api/rest/user/save",
            "/api/rest/user/info"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        // *表示允许所有域名跨域
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Headers","*");
        // 允许跨域的Http方法
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE");
        // 允许浏览器访问 Token 认证响应头
        httpResponse.addHeader("Access-Control-Expose-Headers", TokenConst.TOKEN_HEADERS_FIELD);
        // 默认返回原 Token
        httpResponse.setHeader(TokenConst.TOKEN_HEADERS_FIELD, httpRequest.getHeader(TokenConst.TOKEN_HEADERS_FIELD));
        // 应对探针模式请求(OPTIONS)
        String methodOptions = "OPTIONS";
        if (httpRequest.getMethod().equals(methodOptions)) {
            httpResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        /**
         * 校验用户 Token
         */
        boolean flag = !Arrays.asList(NO_TOKEN_API_PATHS).contains(httpRequest.getRequestURI());
        if (flag) {
            ResponseCode responseCode = checkToken(httpRequest, httpResponse);
            if (!Objects.equals(responseCode, ResponseCode.SUCCESS)) {
                log.warn("{}", responseCode);
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                httpResponse.setContentType("application/json; charset=utf-8");
                httpResponse.setCharacterEncoding("utf-8");
                PrintWriter writer = httpResponse.getWriter();
                writer.write(new ObjectMapper().writeValueAsString(ApiResult.failure(responseCode)));
                return;
            }
        }

        filterChain.doFilter(httpRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 用户 Token 校验
     *
     * @param request
     * @param response
     * @return
     */
    private ResponseCode checkToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            String token = request.getHeader(TokenConst.TOKEN_HEADERS_FIELD);
            if (token == null || token.length() < 1) {
                return ResponseCode.USER_TOKEN_NULL_ERROR;
            }
            String tokenValue = JwtUtil.decode(TokenConst.TOKEN_KEY, token);
            long time = Long.parseLong(tokenValue.substring(tokenValue.indexOf("@") + 1));
            String userPhone = tokenValue.substring(0, tokenValue.indexOf("@"));
            log.info("{}, date: {}, userPhone: {}", tokenValue, new Date(time), userPhone);
            // 校验 Token 有效性
            long subResult = System.currentTimeMillis() - time;
            if (subResult >= TokenConst.TOKEN_EXPIRE_TIME_MILLIS) {
                return ResponseCode.USER_TOKEN_ERROR;
            }
            if (subResult < TokenConst.TOKEN_REFRESH_TIME_MILLIS) {
                return ResponseCode.SUCCESS;
            }
            // 刷新 Token
            String newToken = JwtUtil.encode(TokenConst.TOKEN_KEY,userPhone + "@" + System.currentTimeMillis());
            response.setHeader(TokenConst.TOKEN_HEADERS_FIELD, newToken);
        } catch (Exception e) {
            log.warn("Token 校验失败,{}:{}", e.getClass().getName(), e.getMessage());
            return ResponseCode.USER_TOKEN_ERROR;
        }
        return ResponseCode.SUCCESS;
    }
}
