package com.xhc.entity;

public class HouseHoldBean {
    /**
     * name : 张飞鸿
     * sex : 男
     * nation : 汉
     * pc : 中共党员
     * culture : 硕士
     * id : 13040219940601271X
     * faith : 佛教
     * gridCode : 110
     * tel : 18751809239
     * difficulty : 困难
     * disableId : 无
     * isResident : 是
     * homeAdd : 江苏南京
     * workAdd : 烽火星空
     * age:年龄
     */
//    private int uid;
    private String name;
    private int sex;
    private String nation;
    private String pc;
    private String culture;
    private String id;
    private String faith;
    private String gridCode;
    private String tel;
    private String difficulty;
    private String disableId;
    private int isResident;
    private String homeAdd;
    private String workAdd;
    private String descmsg;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDisableId() {
        return disableId;
    }

    public void setDisableId(String disableId) {
        this.disableId = disableId;
    }

    public int getIsResident() {
        return isResident;
    }

    public void setIsResident(int isResident) {
        this.isResident = isResident;
    }

    public String getHomeAdd() {
        return homeAdd;
    }

    public void setHomeAdd(String homeAdd) {
        this.homeAdd = homeAdd;
    }

    public String getWorkAdd() {
        return workAdd;
    }

    public void setWorkAdd(String workAdd) {
        this.workAdd = workAdd;
    }

    public String getDesc() {
        return descmsg;
    }

    public void setDesc(String desc) {
        this.descmsg = desc;
    }

    @Override
    public String toString() {
        return "HouseHold{" +
//                "uid=" + uid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", pc='" + pc + '\'' +
                ", culture='" + culture + '\'' +
                ", id='" + id + '\'' +
                ", faith='" + faith + '\'' +
                ", gridCode='" + gridCode + '\'' +
                ", tel='" + tel + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", disableId='" + disableId + '\'' +
                ", isResident=" + isResident +
                ", homeAdd='" + homeAdd + '\'' +
                ", workAdd='" + workAdd + '\'' +
                ", desc='" + descmsg + '\'' +
                '}';
    }
}
