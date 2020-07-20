package com.ljq.demo.springboot.baseweb.validate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: java bean 参数校验工具类
 * @Author: junqiang.lu
 * @Date: 2019/1/24
 */
public class BeanValidateUtil {

    private BeanValidateUtil(){}

    /**
     * java bean 数据校验
     * 参数符合要求,返回 null,否则返回错误原因(不包含参数名)
     *
     * @param target 目标 bean
     * @param <T>
     * @return
     */
    public static <T> String validate(T target){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> error = iterator.next();
            return error.getMessage();
        }
        return  null;
    }

    /**
     * java bean 数据校验
     * 参数符合要求,返回 null,否则返回错误原因(包含参数名)
     *
     * @param target 目标 bean
     * @param <T>
     * @return
     */
    public static <T> String validateWithParamter(T target){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(target);
        Iterator<ConstraintViolation<T>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation<T> error = iterator.next();
            StringBuffer buffer = new StringBuffer().append("[")
                    .append(error.getPropertyPath().toString()).append("]")
                    .append(error.getMessage());
            return buffer.toString();
        }
        return  null;
    }
}
