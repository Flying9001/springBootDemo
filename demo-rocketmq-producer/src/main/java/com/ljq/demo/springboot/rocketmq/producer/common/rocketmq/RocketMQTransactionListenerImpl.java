package com.ljq.demo.springboot.rocketmq.producer.common.rocketmq;

import com.ljq.demo.springboot.rocketmq.producer.common.constant.RocketMQConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: RocketMQ 事务监听器
 * @Author: junqiang.lu
 * @Date: 2021/12/6
 */
@Slf4j
@RocketMQTransactionListener()
public class RocketMQTransactionListenerImpl implements RocketMQLocalTransactionListener {

    private ConcurrentHashMap<String, String> localTransaction = new ConcurrentHashMap<>();


    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transactionId = String.valueOf(message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID));
        log.info("RocketMQ local transaction execute, message:{},transactionId: {}, param:{}", message.getPayload(),
                transactionId, o);

        // TODO 处理本地事务
        String transactionParam = String.valueOf(o);
        log.info("transactionParam:{}", transactionParam);
        if (transactionParam.contains(RocketMQConst.TRANSACTION_STATUS_SUCCESS)) {
            localTransaction.put(transactionId, RocketMQConst.TRANSACTION_STATUS_SUCCESS);
            log.info("local transaction handle result: {}",RocketMQConst.TRANSACTION_STATUS_SUCCESS);
            return RocketMQLocalTransactionState.COMMIT;
        }
        if (transactionParam.contains(RocketMQConst.TRANSACTION_STATUS_FAIL)) {
            localTransaction.put(transactionId, RocketMQConst.TRANSACTION_STATUS_FAIL);
            log.info("local transaction handle result: {}",RocketMQConst.TRANSACTION_STATUS_FAIL);
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        log.info("transaction status unknown");
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transactionId = String.valueOf(message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID));
        log.info("RocketMQ local transaction check, message:{},transactionId: {}", message.getPayload(), transactionId);

        // TODO 校验本地事务
        String transactionResult = localTransaction.getOrDefault(transactionId, "");
        if (Objects.equals(transactionResult, RocketMQConst.TRANSACTION_STATUS_SUCCESS)) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        if (Objects.equals(transactionResult, RocketMQConst.TRANSACTION_STATUS_FAIL)) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
        return RocketMQLocalTransactionState.UNKNOWN;
    }
}
