package com.yasso.dfbb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "testdoct", type = "testbean")
public class TestBean {

    @Id
    private long id;
    private String name;
    private Integer age;
    private String sex;
    private String desc;

    public TestBean(long id, String name, Integer age, String sex, String desc) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.desc = desc;
    }

    public TestBean() {
    }
}
