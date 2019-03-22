package com.ljq.demo.springboot.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.beans.BeanMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
     * 将 java 对象装换为map
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

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
                map = beanToMap(bean);
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
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 将 object 对象转换为 Map<String, Object>
     *
     * @param object object 对象
     * @return map
     * @throws IOException
     */
    public static Map<String, Object> objectToMap(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String params = mapper.writeValueAsString(object);
        Map<String, Object> resultMap = mapper.readValue(params, new TypeReference<Map<String,Object>>(){});
        return resultMap;
    }

    /**
     * 判断 map 对象是否为空
     * 当 map != null 且 map 中有值时,返回 true,否则返回 false
     *
     * @param map map 对象
     * @return map 对象为空的判断结果
     */
    public static boolean isEmpty(Map map){
        if(map == null || map.isEmpty()){
            return true;
        }
        return false;
    }




}
