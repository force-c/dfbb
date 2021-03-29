package com.yasso.dfbb.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yasso.dfbb.entity.Log;
import com.yasso.dfbb.mapper.LogMapper;
import com.yasso.dfbb.spring.transaction.Tr1;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author guochuang
 * @version 1.0
 * @date 2021/3/19 17:21
 */
@RestController
@RequestMapping("/web1")
@RequiredArgsConstructor
public class web1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(web1.class);

    private final LogMapper logMapper;
    private final Tr1 tr1;
    private final JdbcTemplate jdbcTemplate;
    private final HikariDataSource hikariDataSource;
    private final ThreadPoolTaskExecutor executor;

    @GetMapping("/w1")
    public Integer w1(){
        return tr1.t1();
    }

    @GetMapping("/w2")
    public void w2() {
//        Log log = logMapper.selectById(ii);
        QueryWrapper<Log> logQueryWrapper = new QueryWrapper<>();
        List<Log> logs = logMapper.selectList(logQueryWrapper);

        System.out.println(logs.toString());
    }

    static volatile boolean nocice = false;
    @GetMapping("/w3")
    public void w3(@RequestParam Integer ii) {
//        Map<String, Object> stringObjectMap = jdbcTemplate.queryForMap("select id from Log where id = ?", ii);
//        jdbcTemplate
        executor.submit(() -> {
            LOGGER.info("90909090");
        });
    }





}
