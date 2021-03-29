package com.yasso.em.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专家库枚举
 * @author guochuang
 * @version 1.0
 * @date 2021/3/29 15:35
 */
public enum EMARepertoryEnum {

    BLOCK_LIST(1, "黑名单专家"),
    CHECKED(2,"已选中专家"),
    TO_PARTICIPATE_IN_THE_REVIEW(3, "参加评审"),
    TO_COMPLETE_IN_THE_REVIEW(4, "已完成评审");


    EMARepertoryEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    private final Integer status;

    private final String description;

    public Integer status() {
        return status;
    }
    public String description() {
        return description;
    }

    public static List<Map<String,Object>> toList(){
        List<Map<String, Object>> list = new ArrayList<>();
        for (EMARepertoryEnum status : EMARepertoryEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("value",status.status());
            map.put("label",status.description());
            list.add(map);
        }
        return list;
    }
}
