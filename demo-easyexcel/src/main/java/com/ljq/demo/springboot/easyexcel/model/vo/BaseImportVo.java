package com.ljq.demo.springboot.easyexcel.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 导入基础参数对象
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
@Data
public class BaseImportVo implements Serializable {

    private static final long serialVersionUID = -1737973316111453122L;

    /**
     * 行号,从 1 开始
     */
    private Integer rowNo;

}
