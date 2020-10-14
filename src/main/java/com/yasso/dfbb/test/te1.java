package com.yasso.dfbb.test;

import org.springframework.stereotype.Service;

@Service
public class te1 {

    public void problem(){
        int k = 9;
        if (k == 9) {
            throw new RuntimeException("出现异常");
        }
    }
}
