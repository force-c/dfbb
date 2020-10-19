package com.yasso.dfbb.es.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    @Qualifier("RestHighLevelClient")
    private RestHighLevelClient client;

    @GetMapping("/CreateIndex")
    @SneakyThrows
    public void CreateIndex(){
        CreateIndexRequest request = new CreateIndexRequest("yuan");

        request.settings(
                Settings.builder()
                .put("index.number_of_shards", 5)
                .put("index.number_of_replicas", 1));


        Map<String, Object> message = new HashMap<>();
        message.put("type", "text");
        Map<String, Object> name = new HashMap<>();
        name.put("type", "keyword");
        Map<String, Object> title = new HashMap<>();
        title.put("type", "text");
        Map<String, Object> properties = new HashMap<>();
        properties.put("message", message);
        properties.put("name", name);
        properties.put("title", title);
        Map<String, Object> mapping = new HashMap<>();
        mapping.put("properties", properties);
        request.mapping(mapping);

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.toString());
    }

    @GetMapping("/existsIndex")
    @SneakyThrows
    public boolean existsIndex(){
        GetIndexRequest yuan = new GetIndexRequest("yuan");
        boolean exists = client.indices().exists(yuan, RequestOptions.DEFAULT);
        return exists;
    }


    @SneakyThrows
    @GetMapping("/CreateIndex2")
    public void CreateIndex2(){
        CreateIndexRequest request = new CreateIndexRequest("kang");
        CreateIndexRequest settings = request.settings(
                Settings.builder()
                        .put("index.number_of_shards", 5)
                        .put("index.number_of_replicas", 1));

        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("message");
                {
                    builder.field("type", "text");
                }
                builder.endObject();
                builder.startObject("title");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.index());

    }

}
