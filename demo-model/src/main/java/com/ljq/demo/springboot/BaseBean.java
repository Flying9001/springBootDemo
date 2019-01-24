package com.ljq.demo.springboot;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @Description: 基础 bean
 * @Author: junqiang.lu
 * @Date: 2018/12/24
 */
@Data
public class BaseBean implements Serializable {

    private static final long serialVersionUID = 6877955227522370690L;

    /**
     * 语言类型
     */
    @Pattern(regexp = "^\\w{2,10}$", message = "api.response.code.languageTypeError")
    private String language;



}
