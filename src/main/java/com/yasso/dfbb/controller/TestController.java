package com.yasso.dfbb.controller;

import com.yasso.dfbb.entity.TestBean;
import com.yasso.dfbb.service.TestService;
import com.yasso.dfbb.spring.event.EventDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("/getList")
    public List<String> getList(){
        List<String> list = new LinkedList<>();
        list.add("one");
//        list.add("two");
//        list.add("three");
        //list.add("one");
        return list;
    }

    @GetMapping("/findAll")
    public Iterable<TestBean> findAll(){
        return testService.findAll();
    }



}
