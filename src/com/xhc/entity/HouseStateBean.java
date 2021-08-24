package com.xhc.entity;

public class HouseStateBean {
    /**
     * homestead : 1421CD4F451
     * coverArea : 134平米
     * houseArea : 100平米
     */
    private int hid;
    private String homestead;
    private String coverArea;
    private String houseArea;
    private String houseid;

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHomestead() {
        return homestead;
    }

    public void setHomestead(String homestead) {
        this.homestead = homestead;
    }

    public String getCoverArea() {
        return coverArea;
    }

    public void setCoverArea(String coverArea) {
        this.coverArea = coverArea;
    }

    public String getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(String houseArea) {
        this.houseArea = houseArea;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    @Override
    public String toString() {
        return "HouseStateBean{" +
                "hid=" + hid +
                ", homestead='" + homestead + '\'' +
                ", coverArea='" + coverArea + '\'' +
                ", houseArea='" + houseArea + '\'' +
                ", houseid='" + houseid + '\'' +
                '}';
    }
}
