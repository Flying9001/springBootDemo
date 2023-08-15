package com.ljq.demo.springboot.knife4j.model.param.userpushtype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户消息推送方式参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 17:17:19
 */
@Data
@Schema(description = "用户消息推送方式保存(单条)")
public class UserPushTypeSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;



    @NotNull(message = "用户id 不能为空")
    @Schema(description = "用户id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @NotNull(message = "推送方式不能为空")
    @Schema(description = "推送方式,1-短信;2-邮件;3-app;4-wechat", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushType;

    @NotBlank(message = "通知推送接收地址 不能为空")
    @Schema(description = "通知推送接收地址", requiredMode = Schema.RequiredMode.REQUIRED)
    private String receiveAddress;

    @NotNull(message = "是否启用不能为空")
    @Schema(description = "是否启用,0-未启用,1-启用", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer enable;


}
