package com.xhc.mapper;

import com.xhc.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IMapper {
    //插入household表
    boolean insertIntoHouseHold(HouseHoldBean houseHoldBean);
    //插入family表
    boolean insertIntoFamily(List<FamilyBean> familyBeanList);
    //插入housestate表
    boolean insertIntoHouseState(HouseStateBean houseStateBean);
    //插入picture表
    boolean insertIntoPicture(List<PictureBean> pictureBeanList);
    //删除household表
    boolean deleteHouseHold();
    //删除family表
    boolean deleteFamily();
    //删除housestate表
    boolean deleteState();
    //删除picture表
    boolean deletePicture();
    //单个查询household数据库（指定个人查询）
    HouseHoldBean queryOnehold(@Param("username") String username, @Param("id") String id);
    //单个查询family
    List<FamilyBean> queryOneFamily(String id);
    //单个查询houseState
    HouseStateBean queryOneState(String id);
    //单个查询picture
    List<PictureBean> queryOnePicture(String id);
    //条件查询，户主表
    List<HouseHoldBean> queryMultiHold(@Param("inputMap") Map<String,Object> map);
    //条件查询，关联表
    List<FamilyBean> queryMultiFamily(@Param("inputMap") Map<String,Object> map);


}
