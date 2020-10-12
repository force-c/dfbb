package com.yasso.dfbb.spring.comment;

import com.yasso.dfbb.spring.event.EventDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事件发布
 */
@RestController
@RequestMapping("/pub")
public class EventDemoPublish {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @RequestMapping("/publish")
    public void publish(String message){
        EventDemo chen = new EventDemo(this, "chen");
        applicationEventPublisher.publishEvent(chen);
    }

}
