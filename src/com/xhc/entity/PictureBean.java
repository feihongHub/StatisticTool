package com.xhc.entity;

public class PictureBean {
    /**
     * base : base64编码
     */
    private int id;
    private String msid;
    private String base;
    private String houseid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsid() {
        return msid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    @Override
    public String toString() {
        return "PictureBean{" +
                "id=" + id +
                ", msid='" + msid + '\'' +
                ", base='" + base + '\'' +
                ", houseid='" + houseid + '\'' +
                '}';
    }
}
