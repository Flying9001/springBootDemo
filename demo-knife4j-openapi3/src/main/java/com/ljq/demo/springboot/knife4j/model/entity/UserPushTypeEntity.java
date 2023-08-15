package com.ljq.demo.springboot.knife4j.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljq.demo.springboot.knife4j.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 用户消息推送方式实体类
 *
 * @author junqiang.lu
 * @date 2023-08-15 14:26:38
 */
@Data
@ToString(callSuper = true)
@Schema(description = "用户消息推送方式")
@TableName(value = "user_push_type")
public class UserPushTypeEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Schema(description = "用户id")
	private Long userId;

	@Schema(description = "推送方式,1-短信;2-邮件;3-app;4-wechat")
	private Integer pushType;

	@Schema(description = "通知推送接收地址")
	private String receiveAddress;

	@Schema(description = "是否启用,0-未启用,1-启用")
	private Integer enable;


}
