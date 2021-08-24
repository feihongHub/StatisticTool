package com.xhc.entity;

public class FamilyBean {
    /**
     * name : 旺达
     * relation : 母子
     * nation : 汉
     * pc : 中共党员
     * culture : 博士
     * id : 13040211141454
     * workAdd : 邯郸市第一医院
     * sex : 女
     * isResident : 是
     * tel : 13111345118
     * difficulty : 困难
     * disableId : 无
     * age:年龄
     */
    private int fid;
    private String name;
    private String relation;
    private String nation;
    private String pc;
    private String culture;
    private String id;
    private String workAdd;
    private int sex;
    private int isResident;
    private String tel;
    private String difficulty;
    private String disableId;
    private String houseId;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public String getWorkAdd() {
        return workAdd;
    }

    public void setWorkAdd(String workAdd) {
        this.workAdd = workAdd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIsResident() {
        return isResident;
    }

    public void setIsResident(int isResident) {
        this.isResident = isResident;
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

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Override
    public String toString() {
        return "FamilyBean{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", nation='" + nation + '\'' +
                ", pc='" + pc + '\'' +
                ", culture='" + culture + '\'' +
                ", id='" + id + '\'' +
                ", workAdd='" + workAdd + '\'' +
                ", sex=" + sex +
                ", isResident=" + isResident +
                ", tel='" + tel + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", disableId='" + disableId + '\'' +
                ", houseId='" + houseId + '\'' +
                '}';
    }

    public String toFamilyString(FamilyBean familyBean) {
        String sexValue = familyBean.getSex() == 0 ? "男" : "女";
        String residentValue = familyBean.getIsResident() == 0 ? "是" : "否";
        return familyBean.getName() + "," + familyBean.getRelation() + "," + familyBean.getNation()
                + "," + familyBean.getPc() + "," + familyBean.getCulture() + "," + familyBean.getId()
                + "," + familyBean.getWorkAdd() + "," + sexValue + "," + residentValue
                + "," + familyBean.getTel() + "," + familyBean.getDifficulty() + "," + familyBean.getDisableId();
    }
}
