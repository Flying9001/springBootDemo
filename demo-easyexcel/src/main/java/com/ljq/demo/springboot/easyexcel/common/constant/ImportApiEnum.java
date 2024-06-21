package com.ljq.demo.springboot.easyexcel.common.constant;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * @Description: 导入接口地址枚举类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Getter
public enum ImportApiEnum {

    /**
     * 导入接口枚举
     */
    IMPORT_STUDENT_API("/api/easyexcel/student/import", "学生信息导入"),
    IMPORT_TEACHER_API("/api/easyexcel/teacher/import", "教师信息导入"),

    UNKNOWN_API("", "未知");
    ;

    /**
     * 接口地址
     */
    private final String apiAddress;

    /**
     * 接口描述
     */
    private final String desc;

    ImportApiEnum(String apiAddress, String desc) {
        this.apiAddress = apiAddress;
        this.desc = desc;
    }

    /**
     * 根据接口地址获取枚举
     *
     * @param apiAddress
     * @return
     */
    public static ImportApiEnum getByApiAddress(String apiAddress) {
        if (StrUtil.isBlank(apiAddress)) {
            return UNKNOWN_API;
        }
        for (ImportApiEnum apiEnum : ImportApiEnum.values()) {
            if (apiEnum.getApiAddress().equals(apiAddress) || apiAddress.contains(apiEnum.getApiAddress())) {
                return apiEnum;
            }
        }
        return UNKNOWN_API;
    }
}
