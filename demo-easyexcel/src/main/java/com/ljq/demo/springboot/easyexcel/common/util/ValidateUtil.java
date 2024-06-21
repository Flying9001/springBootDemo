package com.ljq.demo.springboot.easyexcel.common.util;

import com.ljq.demo.springboot.easyexcel.model.vo.BaseImportVo;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 参数校验工具类
 * @Author: junqiang.lu
 * @Date: 2024/6/20
 */
public class ValidateUtil {

    private ValidateUtil() {
    }

    /**
     * 校验
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> valid(T t){
        Validator validatorFactory = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validatorFactory.validate(t);
        return errors.stream().map(error -> error.getMessage()).collect(Collectors.toList());
    }

    /**
     * 校验 Excel 导入对象
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<String> validExcel(T t){
        Validator validatorFactory = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> errors = validatorFactory.validate(t);
        return errors.stream().map(error -> String.format("第%s行错误:%s",
                ((BaseImportVo)t).getRowNo(),error.getMessage())).collect(Collectors.toList());
    }

}
