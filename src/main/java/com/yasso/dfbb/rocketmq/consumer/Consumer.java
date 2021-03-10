package com.yasso.dfbb.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/21 13:11
 */
@Slf4j
@Component
public class Consumer {

    private final String TOPIC = "the-topic";
    private final String TAG = "the-tag";

    @Value("${rocketmq.consumerGroup}")
    private String consumerGroup;

    @Value("${rocketmq.namesrvaddr}")
    private String namesrvaddr;

//    @PostConstruct
    @ConditionalOnProperty
    public void consumer() {
        System.out.println(consumerGroup);
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvaddr);
        try {
            consumer.subscribe(TOPIC, TAG);
            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
                try {
                    for (MessageExt messageExt : list) {
                        log.info("消费消息 消息体：{}", new String(messageExt.getBody()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //消费失败 尝试重试
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
