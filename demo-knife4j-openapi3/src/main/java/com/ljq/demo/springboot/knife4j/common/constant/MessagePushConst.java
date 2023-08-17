package com.ljq.demo.springboot.knife4j.common.constant;

/**
 * @Description: 消息推送常量
 * @Author: junqiang.lu
 * @Date: 2023/8/16
 */
public class MessagePushConst {

    private MessagePushConst() {

    }

    /**
     * 用户推送方式
     * 1: 短信
     * 2: 邮件
     * 3: app 通知
     * 4: 微信通知
     */
    public static final int USER_PUSH_TYPE_SMS = 1;
    public static final int USER_PUSH_TYPE_EMAIL = 2;
    public static final int USER_PUSH_TYPE_APP = 3;
    public static final int USER_PUSH_TYPE_WECHAT = 4;

    /**
     * 消息发送状态
     * 0: 发送失败
     * 1: 发送成功
     * 2: 未发送
     */
    public static final int MESSAGE_SEND_FAIL = 0;
    public static final int MESSAGE_SEND_SUCCESS = 1;
    public static final int MESSAGE_SEND_NOT = 2;



}
