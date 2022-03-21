package com.example.mq.rocketmq;


import com.example.mq.entry.Person;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class RocketMqController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/test")
    public void test() {
        TransactionSendResult queue_start = rocketMQTemplate.sendMessageInTransaction("dyg-rocket-demo-test", "my-rocketmq-test",
                MessageBuilder.withPayload(new String("queue start")).build(), new Person());

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(null);//nameServer 连接nameSrv

    }

    /**
     * public enum SendStatus {
     * SEND_OK,
     * FLUSH_DISK_TIMEOUT,
     * FLUSH_SLAVE_TIMEOUT,
     * SLAVE_NOT_AVAILABLE,
     * }
     */
    @RequestMapping("/test1")
    public void test1() throws Exception {
        int num = 0;
        //SendStatus
        DefaultMQProducer mqProducer = new DefaultMQProducer("producer-group");
        mqProducer.setNamesrvAddr("ip");
        mqProducer.start();
        Message message = new Message("topic", "tag", "body".getBytes());
        //mqProducer.send(message);
        SendResult send = mqProducer.send(message, null, "key-" + num);
        mqProducer.sendOneway(null);//只发送，结果不管
        mqProducer.send(null, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable e) {

            }
        });
        mqProducer.send(null, new MessageQueueSelector() {
            @Override
            public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                return mqs.get(0);
            }
        }, null);


        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("gp-group");
        consumer.setNamesrvAddr("ip");
        consumer.subscribe("topic", "*");
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                MessageExt messageExt = msgs.get(0);
                if (messageExt.getReconsumeTimes() == 3) {
                    //持久化，定时任务处理
                    //★★★  衰减  默认16次
                    //★★★   DLQ  死信队列
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                return null;
            }
        });
    }

}
