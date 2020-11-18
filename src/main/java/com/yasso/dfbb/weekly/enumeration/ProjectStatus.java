package com.yasso.dfbb.weekly.enumeration;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/11/18 16:36
 */
public enum ProjectStatus {

//    /**
//     *
//     */
//    ProjectNumber("项目编号"),
//
//    /**
//     *项目名称
//     */
//    ProjectName("项目名称"),
//
//    /**
//     *项目代号
//     */
//    ProjectCode("项目代号"),
//
//    /**
//     *项目负责人
//     */
//    ProjectLeader("项目负责人"),
//    /**
//     *截至日期
//     */
//    DEADLINE("截至日期"),
//    /**
//     *项目状态
//     */
//    ProjectState("项目状态"),

    VAL_01("未开始"),
    VAL_02("进行中"),
    VAL_03("已延期");
//    /**
//     *预计
//     */
//    PLAN("预计"),
//    /**
//     *消耗
//     */
//    EXPEND("消耗"),
//    /**
//     *剩余
//     */
//    RESIDUE("剩余"),
//    /**
//     *进度
//     */
//    SCHEDULE("进度");

    private String val;

    ProjectStatus(String val) {
        this.val = val;
    }


    public String getVal() {
        return this.val;
    }


}
