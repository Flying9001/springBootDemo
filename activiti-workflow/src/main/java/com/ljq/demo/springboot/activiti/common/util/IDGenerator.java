package com.ljq.demo.springboot.activiti.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description: ID 生成器
 * @Author: junqiang.lu
 * @Date: 2020/7/9
 */
public class IDGenerator {

    private IDGenerator() {}

    /**
     * 生成 id
     * @return
     */
    public static String getID() {
        StringBuilder no = new StringBuilder();
        no.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")));
        no.append((int) (Math.random() * 90000 + 10000));

        return no.toString();
    }
}
