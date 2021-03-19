package com.yasso.dfbb.weekly.service.impl;

import com.yasso.dfbb.weekly.config.InitMap;
import com.yasso.dfbb.weekly.entity.CellElement;
import com.yasso.dfbb.weekly.enumeration.ProjectStatus;
import com.yasso.dfbb.weekly.service.ProcessingWeekly;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 15:03
 */
@Service
public class ProcessingWeeklyimpl implements ProcessingWeekly {

    /**
     * 全局map 存放所有的元素
     */
    static Map<String,String> element = new HashMap<>();

    @Resource
    InitMap initMap;


    @SneakyThrows
    @Override
    public List<List<ArrayList<CellElement>>> parseExcelContents(String excelUrl) {
        File excelFile = new File(excelUrl);
        Workbook workbook = WorkbookFactory.create(excelFile);
        List<List<ArrayList<CellElement>>> allData = new ArrayList<>();
        // sheet页面处理
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int sheetIndex=0; sheetIndex<numberOfSheets; sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row temp = sheet.getRow(0);
            if (null == temp) {
                continue;
            }
            int cells = temp.getPhysicalNumberOfCells();
            int rowNumbers = sheet.getLastRowNum() + 1;
            List<ArrayList<CellElement>> sheetList = new ArrayList();

            //当前sheet页中的数据行
            for (int rowIndex = 0; rowIndex < rowNumbers; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                ArrayList<CellElement> rowList = new ArrayList<>();

                // 当前数据行中的每一个数据元素
                for (int cellIndex = 0; cellIndex < cells; cellIndex++) {
                    //System.out.println(row.getCell(cellIndex).toString() + "  ");
                    CellElement cellElement = new CellElement(row.getCell(cellIndex).toString(),rowIndex,initMap.getYAxis(cellIndex + 1) , sheet.getSheetName());
                    rowList.add(cellElement);
                    element.put(rowIndex+initMap.getYAxis(cellIndex),cellElement.getElement());
                }
                sheetList.add(rowList);
            }
            allData.add(sheetList);
        }

        return allData;
    }

    @SneakyThrows
    @Override
    public void getResult(List<List<ArrayList<CellElement>>> excelData) {
        List<ArrayList<CellElement>> excelSheet = excelData.get(0);
        excelSheet.forEach(row -> {
            row.forEach(cell -> {

                //逻辑1 检测项目状态 如果项目状态未开始而预计、消耗、剩余大于0 则提醒 相关负责人
                if (cell.getElement().equals(ProjectStatus.VAL_01.getVal())) {
                    Integer yIndex = initMap.ByYAxis(cell.getYAxis()) - 1;
                    //预计 +1
                    String planIndex = cell.getXAxis()+initMap.getYAxis(yIndex + 1);
                    //消耗 +2
                    String expendIndex = cell.getXAxis()+initMap.getYAxis(yIndex + 2);
                    //剩余 +3
                    String residueIndex = cell.getXAxis()+initMap.getYAxis(yIndex + 3);
                    //项目负责人 -1
                    String projectLeaderIndex = cell.getXAxis()+initMap.getYAxis(yIndex - 2);
                    String projectCodeIndex = cell.getXAxis()+initMap.getYAxis(yIndex - 3);
                    String plan = element.get(planIndex);
                    String expend = element.get(expendIndex);
                    String residue = element.get(residueIndex);
                    String projectLeader = element.get(projectLeaderIndex);
                    String projectCode = element.get(projectCodeIndex);
                    if ( !plan.equals("0") || !expend.equals("0") || !residue.equals("0")) {
                        System.err.println("提醒项目负责人  ->  " + projectLeader + "  你的项目状态忘记变更了, 项目代号  ->  " + projectCode);
                    }
                }
            });
        });
    }
}
