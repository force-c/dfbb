package com.yasso.dfbb.weekly.service;

import com.yasso.dfbb.weekly.entity.CellElement;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理周报服务
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 14:49
 */
public interface ProcessingWeekly {

    /**
     * 解析excel中的每一个元素
     * @param excelUrl excel文件路径
     * @return excel中的元素集
     */
    List<List<ArrayList<CellElement>>> parseExcelContents(String excelUrl);

    void getResult(List<List<ArrayList<CellElement>>> excelData);
}
