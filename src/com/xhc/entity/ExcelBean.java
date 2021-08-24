package com.xhc.entity;

import java.util.List;

public class ExcelBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * houseHold : {"name":"张飞鸿","sex":"男","nation":"汉","pc":"中共党员","culture":"硕士","id":"13040219940601271X","faith":"佛教","gridCode":"110","tel":"18751809239","difficulty":"困难","disableId":"无","isResident":"是","homeAdd":"江苏南京","workAdd":"烽火星空"}
         * family : [{"name":"旺达","relation":"母子","nation":"汉","pc":"中共党员","culture":"博士","id":"13040211141454","workAdd":"邯郸市第一医院","sex":"女","isResident":"是","tel":"13111345118","difficulty":"困难","disableId":"无"}]
         * houseState : {"homestead":"1421CD4F451","coverArea":"134平米","houseArea":"100平米"}
         * picture : [{"base":"base64编码1"}]
         * otherCase : 其他详情
         */

        private HouseHoldBean houseHold;
        private HouseStateBean houseState;
        private String otherCase;
        private List<FamilyBean> family;
        private List<PictureBean> picture;

        public HouseHoldBean getHouseHold() {
            return houseHold;
        }

        public void setHouseHold(HouseHoldBean houseHold) {
            this.houseHold = houseHold;
        }

        public HouseStateBean getHouseState() {
            return houseState;
        }

        public void setHouseState(HouseStateBean houseState) {
            this.houseState = houseState;
        }

        public String getOtherCase() {
            return otherCase;
        }

        public void setOtherCase(String otherCase) {
            this.otherCase = otherCase;
        }

        public List<FamilyBean> getFamily() {
            return family;
        }

        public void setFamily(List<FamilyBean> family) {
            this.family = family;
        }

        public List<PictureBean> getPicture() {
            return picture;
        }

        public void setPicture(List<PictureBean> picture) {
            this.picture = picture;
        }

        public static class HouseHoldBean {
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
             */

            private String name;
            private String sex;
            private String nation;
            private String pc;
            private String culture;
            private String id;
            private String faith;
            private String gridCode;
            private String tel;
            private String difficulty;
            private String disableId;
            private String isResident;
            private String homeAdd;
            private String workAdd;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
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

            public String isIsResident() {
                return isResident;
            }

            public void setIsResident(String isResident) {
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
        }

        public static class HouseStateBean {
            /**
             * homestead : 1421CD4F451
             * coverArea : 134平米
             * houseArea : 100平米
             */

            private String homestead;
            private String coverArea;
            private String houseArea;

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
        }

        public static class FamilyBean {
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
             */

            private String name;
            private String relation;
            private String nation;
            private String pc;
            private String culture;
            private String id;
            private String workAdd;
            private String sex;
            private String isResident;
            private String tel;
            private String difficulty;
            private String disableId;

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

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String isIsResident() {
                return isResident;
            }

            public void setIsResident(String isResident) {
                this.isResident = isResident;
            }
            public String getIsResident(){
                return isResident;
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
            public String toFamilyString(FamilyBean familyBean){
                return familyBean.getName()+","+familyBean.getRelation()+","+familyBean.getNation()
                        +","+familyBean.getPc()+","+familyBean.getCulture()+","+familyBean.getId()
                        +","+familyBean.getWorkAdd()+","+familyBean.getSex()+","+familyBean.getIsResident()
                        +","+familyBean.getTel()+","+familyBean.getDifficulty()+","+familyBean.getDisableId();
            }
        }

        public static class PictureBean {
            /**
             * base : base64编码1
             */

            private String base;
            private String msid;

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
        }
    }

}
