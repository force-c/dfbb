package com.yasso.dfbb.es.utils;

import com.yasso.dfbb.es.entity.ESSearchParam;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ES7QueryTools {

    private static Logger log = LoggerFactory.getLogger(ES7QueryTools.class);

    @Autowired
    @Qualifier("RestHighLevelClient")
    private RestHighLevelClient client;


    public SearchResponse getResponse(ESSearchParam esSearchParam){

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        if (esSearchParam.getFrom() != null ){
            searchSourceBuilder.from(esSearchParam.getFrom());
        }
        if (esSearchParam.getSize() != null){
            searchSourceBuilder.size(esSearchParam.getSize());
        }
        if (esSearchParam.getQueryBuilder() != null){
            searchSourceBuilder.query(esSearchParam.getQueryBuilder());
        }
        if (esSearchParam.getIndex() != null && !"".equals(esSearchParam.getIndex())){
            SearchRequest request = new SearchRequest(esSearchParam.getIndex());
            request.source(searchSourceBuilder);
            try {
                SearchResponse response = client.search(request, RequestOptions.DEFAULT);
                log.info("searchsql------   "+searchSourceBuilder.toString());
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
