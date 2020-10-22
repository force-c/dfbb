package com.yasso.dfbb.es.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/21 16:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "movies")
public class Movies {

    private String id;
    private String title;
    private String[] genre;
    private long year;
}
