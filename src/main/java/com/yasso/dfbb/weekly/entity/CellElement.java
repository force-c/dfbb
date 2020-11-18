package com.yasso.dfbb.weekly.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel 中的每个单元格
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CellElement {

    /**
     * 单元格的值
     */
    private String element;

    /**
     * 单元格所处横列（1、2、3...）
     */
    private Integer xAxis;

    /**
     * 单元格所处纵列（A、B、C...）
     */
    private String yAxis;

    /**
     * 单元格所处sheet页
     */
    private String sheet;
}
