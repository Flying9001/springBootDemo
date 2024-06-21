package com.ljq.demo.springboot.easyexcel.model.response;

import lombok.Data;

import java.util.List;

/**
 * @Description: 公共导入返回对象
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Data
public class CommonImportResponse {

    /**
     * 成功条数
     */
    private Integer successCount;

    /**
     * 失败原因列表,格式: 第xx行:错误原因
     */
    private List<String> failReasonList;

}
