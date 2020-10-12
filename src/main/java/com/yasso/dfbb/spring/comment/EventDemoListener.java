package com.yasso.dfbb.spring.comment;

import com.yasso.dfbb.spring.event.EventDemo;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {

    /**
     * 当容器发布此事件后方法触发
     * @param event
     */
    @Override
    public void onApplicationEvent(EventDemo event) {
        System.out.println("收到事件" + event.getMessage());
    }
}
