package com.yasso.dfbb.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/21 15:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ESResponse {
    private Integer code;
    private String data;
    private List<String> listData;
}
