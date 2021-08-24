package com.xhc.utils;

import com.xhc.entity.ExcelBean;

public class Constant {
    public static ExcelBean excelBean = null;

    public static String[] masterMsg = {"户主姓名", "性别", "民族", "政治面貌", "文化程度", "身份证号","宗教信仰",
            "网格编码","联系电话","困难情况","残疾证号","是否常住","家庭住址","工作单位"};

    public static String[] familyMsg = {"姓名","关系","民族","政治面貌","文化程度","身份证号","工作单位","性别",
            "是否常住","联系电话","困难情况","残疾证号"};

    public static String[] imgMsg = {"图片"};
    public static String[] houseStateMsg = {"宅基地证号","占地面积","房屋面积"};
    public static String[] otherCaseMsg = {"家庭其他情况"};

    //条件查询result
    public static String[] conditionMsg = {"姓名", "年龄","身份证号","性别","联系电话","文化程度","困难情况","残疾证号","是否常住","民族","政治面貌","工作单位"};
    //mock数据
    //户主信息
    Object[][] masterRowData = {
            {"zhagn", "男", "汉",
                    "党员", "大专", "130402154",
                    "无","201","134512",
                    "困难","447","false",
                    "江苏南京","河北邯郸"}
    };
    //家庭基本信息
    Object[][] familyRowData = {
            {"旺达", "之妻", "汉",
                    "党员", "博士", "130402154",
                    "学校","女","否",
                    "1875080","困难","134"}
    };
    //图片基本信息
    Object[][] imgRowData = {
            {"ddd"}
    };
    //图片基本信息
    Object[][] otherRowData = {{"无"}};

}

