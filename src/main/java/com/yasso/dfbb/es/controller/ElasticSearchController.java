package com.yasso.dfbb.es.controller;

import com.yasso.dfbb.es.entity.ESResponse;
import com.yasso.dfbb.es.entity.ESSearchParam;
import com.yasso.dfbb.es.entity.Movies;
import com.yasso.dfbb.es.repository.MoviesRepository;
import com.yasso.dfbb.es.utils.ES7QueryTools;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
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
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/es")
public class ElasticSearchController {


    private static final String SUGGEST_NAME = "title_suggest";
    private static final String INDEX_NAME = "movies";
    private static final String FIELD = "title";
    private static final Integer OK = 20000;

    @Autowired
    @Qualifier("RestHighLevelClient")
    private RestHighLevelClient client;

    @Autowired
    private ES7QueryTools es7QueryTools;

    @Autowired
    private MoviesRepository moviesRepository;

    @GetMapping("/getString")
    public String getString(){
        return "test docker successful";
    }


    @GetMapping("/getIp")
    public String getIpAndPort(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @GetMapping("/suggest")
    public ESResponse suggest(@RequestParam String prefix) throws IOException {

        ArrayList<String> re = new ArrayList<>();

        // 构建 completion builder  构建前缀 查询数据量  skipDuplicates 好像是自动补全
        CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(FIELD)
                .prefix(prefix)
                .skipDuplicates(true)
                .size(100);
        //构建 suggest builder 指定 name  suggest builder 子类
        SuggestBuilder suggestBuilder = new SuggestBuilder()
                .addSuggestion(SUGGEST_NAME, completionSuggestionBuilder);

        // 构建 search source builder 把suggest条件加载进去 设置不查询原始数据
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder()
                .suggest(suggestBuilder)
                .fetchSource(false);

        //构建查询请求
        SearchRequest request = new SearchRequest(INDEX_NAME);
        //请求加载 资源 builder
        request.source(sourceBuilder);
        //客户端发送请求
        SearchResponse searchResponse = client.search(request, RequestOptions.DEFAULT);
        log.info("searchsql------   "+sourceBuilder.toString());
        //处理响应结果
        List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>>
                entries = searchResponse
                .getSuggest()
                .getSuggestion(SUGGEST_NAME)
                .getEntries();

         entries.forEach(entry -> {
             List<? extends Suggest.Suggestion.Entry.Option> options = entry.getOptions();
             options.forEach(o -> {
                 re.add(o.getText().toString());
             });
         });


        return new ESResponse(OK,null,re);
    }

    @GetMapping("/findByTitle")
    public List<Movies> findByTitle(@RequestParam String title,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Movies> movies = moviesRepository.findByTitle(title, pageable);
        return movies.getContent();
    }

    @PostMapping("/byParams")
    public ESResponse byParams(ESSearchParam esSearchParam){
        esSearchParam.setQueryBuilder(new TermQueryBuilder(esSearchParam.getFieldName(),esSearchParam.getVal()));
        SearchResponse response = es7QueryTools.getResponse(esSearchParam);
        return new ESResponse(OK,response.toString(),null);
        //return "nih";
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
