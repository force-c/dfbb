package com.yasso.dfbb.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.yasso.dfbb.mq.rocketmq.TheMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/21 11:21
 */
@Slf4j
@Component
public class Producer {

    private final String TOPIC = "the-topic";
    private final String TAG = "the-tag";

    @Value("${rocketmq.producerGroup}")
    private String producerGroup;

    @Value("${rocketmq.namesrvaddr}")
    private String namesrvaddr;

//    @PostConstruct
    public void product() {
        System.out.println(producerGroup);
        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvaddr);

        try {
            producer.start();
            for (int i = 0; i < 6000; i++) {
                TheMessage theMessage = new TheMessage("name" + i, i);
                String theMessageJsonStr = JSON.toJSONString(theMessage);
                Message message = new Message(TOPIC, TAG, theMessageJsonStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(message);
                log.info("消息发送成功 MsgId：{} 发送状态：{}", sendResult.getMsgId(),sendResult.getSendStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            producer.shutdown();
        }

    }
}
