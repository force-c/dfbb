package com.yasso.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author guoc
 * @version 1.0
 * @date 2021/2/2 18:52
 */
@Configuration
public class EsConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port1}")
    private Integer port1;

    @Value("${elasticsearch.port2}")
    private Integer port2;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient22() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(host, port1, scheme),
                                   new HttpHost(host, port2, scheme)));
    }

}
