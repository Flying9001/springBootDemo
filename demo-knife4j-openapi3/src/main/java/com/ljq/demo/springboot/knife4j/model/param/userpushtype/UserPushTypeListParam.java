package com.ljq.demo.springboot.knife4j.model.param.userpushtype;

import com.ljq.demo.springboot.knife4j.model.BasePageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户消息推送方式参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 17:17:19
 */
@Data
@Schema(description = "用户消息推送方式查询列表")
public class UserPushTypeListParam extends BasePageParam {

    private static final long serialVersionUID = 1L;

    @Schema(description = "id ")
    private Long id;



}
