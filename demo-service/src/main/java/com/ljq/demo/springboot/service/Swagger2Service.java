package com.ljq.demo.springboot.service;

import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.vo.swagger2.ModelAnnotationBean;

/**
 * @Description: Swagger2 业务接口
 * @Author: junqiang.lu
 * @Date: 2019/3/23
 */
public interface Swagger2Service {

    /**
     * Swagger2 参数接受实体类注解@ApiModel...示例
     *
     * @param modelAnnotationBean
     * @return
     * @throws Exception
     */
    ApiResult modelAnnotation(ModelAnnotationBean modelAnnotationBean) throws Exception;

}
