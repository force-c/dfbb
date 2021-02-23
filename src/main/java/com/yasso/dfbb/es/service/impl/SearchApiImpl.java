package com.yasso.dfbb.es.service.impl;

import com.yasso.dfbb.es.service.SearchApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author guochuang
 * @version 1.0
 * @date 2021/2/23 16:54
 */
@Service
@RequiredArgsConstructor
public class SearchApiImpl implements SearchApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchApiImpl.class);

     RestHighLevelClient restHighLevelClient;

    @SneakyThrows
    @Override
    public void sortSearch() {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("", "");
        searchSourceBuilder.query(matchQueryBuilder);


        // 关闭源索引
        searchSourceBuilder.fetchSource(false);
        // 支持n个通配符模式的数组，更新粒度的控制哪些字段被包括或排除
        String[] includeFields = new String[] {"title", "object"};
        String[] excludeFields = new String[] {"user"};
        searchSourceBuilder.fetchSource(includeFields, excludeFields);


        // 配置请求高亮显示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highLightTitle = new HighlightBuilder.Field("title");
        // 设置字段高亮类型
        highLightTitle.highlighterType("unified");
        highlightBuilder.field(highLightTitle);


        // 请求聚合
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("").field("");
        aggregationBuilder.subAggregation(AggregationBuilders.avg("average_age").field("age"));

        // 建议请求
        TermSuggestionBuilder termSuggestionBuilder = SuggestBuilders.termSuggestion("").text("");
        SuggestBuilder suggestBuilder = new SuggestBuilder()
                .addSuggestion("suggest_user", termSuggestionBuilder);

        // 分析查询和聚合
        // 在使用profile api的时候 必须将SearchSourceBuilder实例中的配置标志为true
        searchSourceBuilder.profile(true);



        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.aggregation(aggregationBuilder);
        searchSourceBuilder.suggest(suggestBuilder);


        searchRequest.source(searchSourceBuilder);
        ScoreSortBuilder order = new ScoreSortBuilder().order(SortOrder.ASC);


        // 同步执行
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        // 异步执行
        // 构建监听器
        ActionListener<SearchResponse> responseActionListener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                LOGGER.info("result" + searchResponse.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        };
        restHighLevelClient.searchAsync(searchRequest, RequestOptions.DEFAULT, responseActionListener);


    }

    private void processSearchResponse(SearchResponse searchResponse) {
        if (searchResponse == null) {
            return;
        }
        // 获取http响应状态码
        RestStatus status = searchResponse.status();
        // 获取请求执行时长
        TimeValue took = searchResponse.getTook();
        // 获取请求是否提前终止
        Boolean terminatedEarly = searchResponse.isTerminatedEarly();
        // 获取请求是否超时
        boolean timedOut = searchResponse.isTimedOut();

        // 查看搜索影响到的分片的总数
        int totalShards = searchResponse.getTotalShards();
        // 搜索成功的分片的统计信息
        int successfulShards = searchResponse.getSuccessfulShards();

    }



}
