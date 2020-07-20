package com.ljq.demo.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ljq.demo.springboot.baseweb.api.ApiResult;
import com.ljq.demo.springboot.common.page.PageUtil;
import com.ljq.demo.springboot.common.page.QueryUtil;
import com.ljq.demo.springboot.dao.user.UserDao;
import com.ljq.demo.springboot.entity.UserDO;
import com.ljq.demo.springboot.service.Swagger2Service;
import com.ljq.demo.springboot.vo.swagger2.ModelAnnotationBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description: Swagger2 业务具体实现类
 * @Author: junqiang.lu
 * @Date: 2019/3/23
 */
@Service("swagger2Service")
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class Swagger2ServiceImpl implements Swagger2Service {

    @Autowired
    private UserDao userDao;

    /**
     * Swagger2 参数接受实体类注解@ApiModel...示例
     *
     * @param modelAnnotationBean
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult modelAnnotation(ModelAnnotationBean modelAnnotationBean) throws Exception {
        /**
         * 获取参数
         */
        Map<String, Object> map = BeanUtil.beanToMap(modelAnnotationBean);
        QueryUtil queryUtil = new QueryUtil(map);
        // 列表查询
        List<UserDO> userDBList = userDao.queryListComplex(queryUtil);
        if (userDBList == null || userDBList.isEmpty()) {
            return ApiResult.success(new PageUtil(null, 0, queryUtil.getPageSize(), queryUtil.getCurrentPage()));
        }

        int total = userDao.countComplex(queryUtil);
        // 分页处理
        PageUtil pageUtil = new PageUtil(userDBList, total, queryUtil.getPageSize(), queryUtil.getCurrentPage());

        return ApiResult.success(pageUtil);
    }
}
