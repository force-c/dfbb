package com.yasso.dfbb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/getList")
    public List<String> getList(){
        List<String> list = new LinkedList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("one");
        return list;
    }
}
