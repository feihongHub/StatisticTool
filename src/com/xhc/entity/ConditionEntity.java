package com.xhc.entity;

public class ConditionEntity {
    private String name;
    private String id;
    private int sex;
    private String nation;
    private String pc;
    private String culture;
    private String faith;
    private String tel;
    private String difficulty;
    private String disableid;
    private int isResident;
    private String homeAdd;
    private String workAdd;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
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

    public String getDisableid() {
        return disableid;
    }

    public void setDisableid(String disableid) {
        this.disableid = disableid;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ConditionEntity{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex=" + sex +
                ", nation='" + nation + '\'' +
                ", pc='" + pc + '\'' +
                ", culture='" + culture + '\'' +
                ", faith='" + faith + '\'' +
                ", tel='" + tel + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", disableid='" + disableid + '\'' +
                ", isResident=" + isResident +
                ", homeAdd='" + homeAdd + '\'' +
                ", workAdd='" + workAdd + '\'' +
                ", age=" + age +
                '}';
    }
}
