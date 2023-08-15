package com.ljq.demo.springboot.knife4j.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ljq.demo.springboot.knife4j.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 用户消息实体类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@ToString(callSuper = true)
@Schema(description = "用户消息")
@TableName(value = "user_message")
public class UserMessageEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;


	@Schema(description = "用户信息")
	private Long userId;

	@Schema(description = "实际消息推送次数")
	private Integer pushCount;

	@Schema(description = "总共消息所需推送次数")
	private Integer pushTotal;

	@Schema(description = "消息类型;1-登录通知;2-费用通知;3-服务器报警")
	private Integer messageType;

	@Schema(description = "消息标题")
	private String title;

	@Schema(description = "消息内容")
	private String content;

}
