package com.ljq.demo.springboot.knife4j.model.param.messagepushresult;

import com.ljq.demo.springboot.knife4j.model.BaseInfoParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 消息推送结果参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@Schema(description = "消息推送结果查询详情(单条)")
public class MessagePushResultInfoParam extends BaseInfoParam {

    private static final long serialVersionUID = 1L;

}
