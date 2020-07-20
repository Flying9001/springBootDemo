package com.ljq.demo.springboot.common.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: java bean 与 map 互相转换工具类
 * @Author: junqiang.lu
 * @Date: 2018/5/14
 */
public class MapUtil {

    private MapUtil(){}

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     * @return
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) throws Exception{
        List<Map<String, Object>> list = new ArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = BeanUtil.beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                bean = BeanUtil.mapToBean(map, clazz, true);
                list.add(bean);
            }
        }
        return list;
    }


}
