package com.yasso.dfbb.designmode.templateMethod;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/10 9:47
 */
public abstract class Father {

    public abstract void f1();
    public abstract void f2();
    public abstract void f3();

    final public void invoke() {
        this.f1();
        this.f2();
        this.f3();
    }
}
