package com.ljq.demo.springboot.knife4j.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljq.demo.springboot.knife4j.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 消息推送结果实体类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@ToString(callSuper = true)
@Schema(description = "消息推送结果")
@TableName(value = "message_push_result")
public class MessagePushResultEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "消息id")
	private Long messageId;

	@Schema(description = "推送方式,1-短信;2-邮件;3-app;4-wechat")
	private Integer pushType;

	@Schema(description = "推送结果,0-失败,1-成功,2-未推送")
	private Integer pushResult;

	@Schema(description = "推送记录值,部分推送方式可根据记录值查询实际推送结果")
	private String pushRecord;

	@Schema(description = "消息发送失败重试次数")
	private Integer retryTime;

}
