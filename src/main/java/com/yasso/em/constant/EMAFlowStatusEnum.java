package com.yasso.em.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专家管理 流程状态
 * @author guochuang
 * @version 1.0
 * @date 2021/3/29 16:02
 */
public enum EMAFlowStatusEnum {

    COMMIT(1, "提交"),
    SEND_BACK(2, "退回"),
    WITHDRAW(3, "提回"),
    ADD_REPOSITORY(4, "入库"),
    REFUSE_ADD_REPOSITORY(5, "不入库"),
    TO_RECOMMEND_NO(0,"重新推荐-否"),
    TO_RECOMMEND_YES(1,"重新推荐-是"),
    PAUSE_TO_RECOMMEND_NO(0, "暂停推荐-否"),
    PAUSE_TO_RECOMMEND_YES(0, "暂停推荐-是");


    EMAFlowStatusEnum(Integer status, String description) {
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
