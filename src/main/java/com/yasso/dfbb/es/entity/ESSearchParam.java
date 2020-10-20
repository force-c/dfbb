package com.yasso.dfbb.es.entity;

import org.elasticsearch.index.query.QueryBuilder;

import java.io.Serializable;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/10/20 18:16
 */
public class ESSearchParam implements Serializable {

    private Integer from;

    private Integer size;

    private QueryBuilder queryBuilder;

    private String index;

    private String fieldName;

    private String val;

    public ESSearchParam() {
    }

    public ESSearchParam(Integer from, Integer size, QueryBuilder queryBuilder, String index, String fieldName, String val) {
        this.from = from;
        this.size = size;
        this.queryBuilder = queryBuilder;
        this.index = index;
        this.fieldName = fieldName;
        this.val = val;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public QueryBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public void setQueryBuilder(QueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
