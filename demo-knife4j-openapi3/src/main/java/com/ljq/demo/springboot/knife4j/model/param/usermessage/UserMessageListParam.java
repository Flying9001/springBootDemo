package com.ljq.demo.springboot.knife4j.model.param.usermessage;

import com.ljq.demo.springboot.knife4j.model.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户消息参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "用户消息查询列表")
public class UserMessageListParam extends BasePageParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id ")
    private Long id;



}
