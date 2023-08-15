package com.ljq.demo.springboot.knife4j.model.param.usermessage;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户消息参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "用户消息保存(单条)")
public class UserMessageSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;



    @NotNull(message = "用户信息 不能为空")
    @Schema(description = "用户信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @NotNull(message = "实际消息推送次数 不能为空")
    @Schema(description = "实际消息推送次数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushCount;

    @NotNull(message = "总共消息所需推送次数 不能为空")
    @Schema(description = "总共消息所需推送次数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushTotal;

    @NotNull(message = "消息类型不能为空")
    @Schema(description = "消息类型;1-登录通知;2-费用通知;3-服务器报警", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer messageType;

    @NotBlank(message = "消息标题 不能为空")
    @Schema(description = "消息标题", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "消息内容 不能为空")
    @Schema(description = "消息内容", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;



}
