package com.ljq.demo.springboot.knife4j.model.param.messagepushresult;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 消息推送结果参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "消息推送结果保存(单条)")
public class MessagePushResultSaveParam implements Serializable {

    private static final long serialVersionUID = 1L;


    @NotNull(message = "消息id 不能为空")
    @Schema(description = "消息id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long messageId;

    @NotNull(message = "推送方式不能为空")
    @Schema(description = "推送方式,1-短信;2-邮件;3-app;4-wechat", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushType;

    @NotNull(message = "推送结果不能为空")
    @Schema(description = "推送结果,0-失败,1-成功,2-未推送", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer pushResult;

    @Schema(description = "推送记录值,部分推送方式可根据记录值查询实际推送结果")
    private String pushRecord;

    @NotNull(message = "消息发送失败重试次数 不能为空")
    @Schema(description = "消息发送失败重试次数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer retryTime;


}
