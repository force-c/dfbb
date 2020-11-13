package com.yasso.dfbb.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/2 17:04
 */
public class FirstVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("hello");
        }).listen(9876);
    }
}
