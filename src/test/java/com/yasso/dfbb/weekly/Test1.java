package com.yasso.dfbb.weekly;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yasso.dfbb.mapper.LogMapper;
import com.yasso.dfbb.web.web1;
import com.yasso.dfbb.weekly.entity.CellElement;
import com.yasso.dfbb.weekly.service.ProcessingWeekly;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 15:39
 */
@SpringBootTest
public class Test1 {


    @Resource
    ProcessingWeekly processingWeekly;

    @Resource
    web1 web1;

    @Resource
    LogMapper logMapper;

    @Test
    public void te1() {
        String url = "C:\\Users\\guochuang\\Desktop\\bb.xlsx";
        List<List<ArrayList<CellElement>>> lists = processingWeekly.parseExcelContents(url);
        processingWeekly.getResult(lists);
    }

    @Test
    public void te2() {
        Assertions.assertNotNull(logMapper.selectList(new QueryWrapper<>()), "e1");
        Assertions.assertNull(logMapper.selectById(12), "e2");

    }

    @Test
    public void te3() {
        Assertions.assertNotNull(logMapper.selectList(new QueryWrapper<>()), "e1");
        Assertions.assertNull(logMapper.selectById(12), "e2");

    }

}
