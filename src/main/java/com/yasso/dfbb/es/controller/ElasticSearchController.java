package com.yasso.dfbb.es.controller;

import com.yasso.dfbb.es.entity.ESSearchParam;
import com.yasso.dfbb.es.utils.ES7QueryTools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/es")
public class ElasticSearchController {

    @Autowired
    @Qualifier("RestHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ES7QueryTools es7QueryTools;

    @PostMapping("/byParams")
    public String byParams(ESSearchParam esSearchParam){
        esSearchParam.setQueryBuilder(new TermQueryBuilder(esSearchParam.getFieldName(),esSearchParam.getVal()));
        SearchResponse response = es7QueryTools.getResponse(esSearchParam);
        return response.toString();
    }


    @GetMapping("/AllIndexs")
    public List<String> AllIndexs(){
        ArrayList<String> indexs = new ArrayList<>();
        GetAliasesRequest request = new GetAliasesRequest();
        try {
            GetAliasesResponse alias = client.indices().getAlias(request, RequestOptions.DEFAULT);


            Map<String, Set<AliasMetaData>> aliases = alias.getAliases();
            for (String s : aliases.keySet()) {
                indexs.add(s);
            }
            return indexs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


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
