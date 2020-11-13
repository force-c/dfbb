package com.yasso.dfbb.enums;

import lombok.Data;

/**
 * TJ 字典使用
 * @author guochaung
 * @version 1.0
 * @date 2020/11/10 14:49
 */
public enum TJ001 {

    /**
     *事假
     */
    TJ001_001("1","事假"),

    /**
     *年假
     */
    TJ001_002("2","带薪回国休假"),

    /**
     *病假
     */
    TJ001_003("3","赴第三国休假"),

    /**
     *病假
     */
    TJ001_004("4","赴第三国参加学术会议或进行短期学术考察");

    private String id;
    private String desc;

    TJ001(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

}
