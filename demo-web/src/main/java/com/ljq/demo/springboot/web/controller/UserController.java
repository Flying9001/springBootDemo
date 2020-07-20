package com.ljq.demo.springboot.web.controller;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.baseweb.api.ApiResultI18n;
import com.ljq.demo.springboot.baseweb.api.ResponseCodeI18n;
import com.ljq.demo.springboot.baseweb.exception.ParamsCheckException;
import com.ljq.demo.springboot.common.annotation.LogConfig;
import com.ljq.demo.springboot.service.UserService;
import com.ljq.demo.springboot.vo.UserSignUpBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户控制中心
 * @Author: junqiang.lu
 * @Date: 2018/10/9
 */
@RestController
@RequestMapping("api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 列表查询,post 请求, json 参数
     *
     * @param params
     * @return
     */
    @LogConfig()
    @RequestMapping(value = "list", method = {RequestMethod.POST})
    public ApiResult queryList(@RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

    /**
     * 列表查询,post 请求, parameter 参数
     *
     * @param params
     * @return
     */
    @LogConfig(ignoreInput = true)
    @RequestMapping(value = "lists", method = {RequestMethod.POST})
    public ApiResult queryLists(Map<String, Object> params){

        return userService.queryList(params);
    }

    /**
     * 列表查询, get 请求
     * @return
     */
    @LogConfig(ignoreOutput = true)
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public ApiResult queryList(){
        Map<String, Object> params = new HashMap<>();
        return userService.queryList(params);
    }

    /**
     * 信息查询, @PathVariable 与 json 参数混合
     *
     * @param id
     * @param params
     * @return
     */
    @LogConfig(ignoreInput = true, ignoreOutput = true)
    @RequestMapping(value = "info/{id}",method = RequestMethod.POST)
    public ApiResult info(@PathVariable("id") long id, @RequestBody Map<String, Object> params){

        return userService.queryList(params);
    }

    /**
     * 用户注册
     *
     * @param userSignUpBean 注册信息
     * @return
     */
    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public ApiResultI18n signUp(@RequestBody UserSignUpBean userSignUpBean){
        try {
            return userService.signUp(userSignUpBean);
        } catch (Exception e) {
            if (ParamsCheckException.class.isAssignableFrom(e.getClass())){
                logger.error("注册失败,参数错误");
                return ApiResultI18n.failure(ResponseCodeI18n.PARAM_ERROR.getCode(), e.getMessage(),
                        userSignUpBean.getLanguage());
            }
            logger.error("注册失败,未知异常",e);
            return ApiResultI18n.failure(ResponseCodeI18n.UNKNOWN_ERROR, userSignUpBean.getLanguage());
        }
    }

}
