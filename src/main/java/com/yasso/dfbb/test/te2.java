package com.yasso.dfbb.test;

import javax.annotation.Resource;

public class te2 {

    @Resource
    private te1 te1;


    public void nn(){
        try {
            te1.problem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
