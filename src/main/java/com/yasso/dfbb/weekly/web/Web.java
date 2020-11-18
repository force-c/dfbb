package com.yasso.dfbb.weekly.web;

import com.yasso.dfbb.weekly.entity.CellElement;
import com.yasso.dfbb.weekly.service.ProcessingWeekly;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 16:19
 */
@RestController
public class Web {

    @Resource
    ProcessingWeekly processingWeekly;

    @GetMapping("/get")
    public List<List<ArrayList<CellElement>>> get() {
        String url = "C:\\Users\\guochuang\\Desktop\\bb.xlsx";
        List<List<ArrayList<CellElement>>> lists = processingWeekly.parseExcelContents(url);
        processingWeekly.getResult(lists);
        return  processingWeekly.parseExcelContents(url);

    }


}
