package com.yasso.dfbb.designmode.templateMethod;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/10 9:50
 */
public class Children extends Father {
    @Override
    public void f1() {
        System.out.println("this is f1");
    }

    @Override
    public void f2() {
    }

    @Override
    public void f3() {
        System.out.println("this is f3");
    }
}
