package com.yasso.dfbb.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/21 16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movies {

    private String id;
    private String title;
    private String[] genre;
    private long year;
}
