package com.yasso.dfbb.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/2 16:32
 */
public class Demo {

    public void demo1() {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(FirstVerticle.class.getName());
    }

}
