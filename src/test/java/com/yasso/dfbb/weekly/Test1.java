package com.yasso.dfbb.weekly;

import com.yasso.dfbb.weekly.entity.CellElement;
import com.yasso.dfbb.weekly.service.ProcessingWeekly;
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

    @Test
    public void te1() {
        String url = "C:\\Users\\guochuang\\Desktop\\bb.xlsx";
        List<List<ArrayList<CellElement>>> lists = processingWeekly.parseExcelContents(url);
        processingWeekly.getResult(lists);
    }
}
