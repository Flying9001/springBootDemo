package com.ljq.demo.springboot.knife4j.model.param.messagepushresult;

import com.ljq.demo.springboot.knife4j.model.BaseInfoParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * 消息推送结果参数接收类
 *
 * @author junqiang.lu
 * @date 2023-08-14 18:06:40
 */
@Data
@ToString(callSuper = true)
@Schema(description = "消息推送结果删除(单条)")
public class MessagePushResultDeleteParam extends BaseInfoParam {

    private static final long serialVersionUID = 1L;

}
