package com.yasso.dfbb.web;

import com.yasso.dfbb.spring.transaction.Tr1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 17:21
 */
@RestController
@RequestMapping("/web1")
@RequiredArgsConstructor
public class web1 {

    private final Tr1 tr1;

    @GetMapping("/w1")
    public Integer w1(){
        return tr1.t1();
    }

}
