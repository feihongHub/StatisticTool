package com.xhc.utils;

import com.xhc.entity.FamilyBean;
import com.xhc.entity.HouseHoldBean;
import com.xhc.entity.HouseStateBean;
import com.xhc.entity.PictureBean;
import com.xhc.mapper.IMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class InputMsg {
    /**
     * 将excel表格中的内容读取出来存入pgsql中。
     *
     * @param filePath
     * @throws IOException
     */
    public static void packageExcelToDataBase(String filePath) throws IOException {

        File file = new File(filePath);
        if (!file.exists()) {//当前文件夹不存在时
            file.mkdir();//创建文件夹
        }
        //遍历该文件夹下的所有文件
        File[] files = file.listFiles();
        //将所有的Excel流读取进来
        for (File item : files) {
            if (!item.getName().endsWith(".xls")) {
                continue;
            }
            InputStream in = new FileInputStream(item);
            Workbook sheets = null;
            try {
                //有待修正bug，当excel版本为更高时候，存在暴雷现象
                sheets = WorkbookFactory.create(in);
            } catch (Exception ex) {
                sheets = new XSSFWorkbook(in);
            }
            // 获取每个表单Sheet
            int numberOfSheets = sheets.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //每个sheet对应一个HouseHoldBean
                HouseHoldBean houseHoldBean = new HouseHoldBean();
                //每个sheet对应一个HouseStateBean
                HouseStateBean houseStateBean = new HouseStateBean();
                //每个sheet对应多个familyBean
                ArrayList<FamilyBean> familyList = new ArrayList<>();

                HSSFSheet itemSheet = (HSSFSheet) sheets.getSheetAt(i);

                //获取标题行，默认第一行
                Row row = itemSheet.getRow(0);
                if (isEmpty(row) || !"西郝村村民登记卡片".equals(transToString(row.getCell(0)))) {
                    continue;
                }
                //获取户主信息
                Row masterMsg = itemSheet.getRow(2);
                //将户主基本信息封装入JSON中
                packageBaseInfo(houseHoldBean, masterMsg);
                Row masterMsg2 = itemSheet.getRow(3);
                houseHoldBean.setHomeAdd(transToString(masterMsg2.getCell(1)));
                houseHoldBean.setWorkAdd(transToString(masterMsg2.getCell(5)));

                //将成员信息封装入JSON中，由于不确定到底有多少个成员，因此需要利用for循环+if条件判断
                int count = 6;
                while (true) {
                    if ("房屋情况".equals(itemSheet.getRow(count).getCell(0).toString())) {
                        break;
                    }
                    //每一行代表一个family成员
                    FamilyBean familyBean = new FamilyBean();
                    //封装family成员方法
                    packageFamilyInfo(familyBean, itemSheet.getRow(count));
                    familyBean.setHouseId(houseHoldBean.getId());
                    if (Objects.nonNull(familyBean.getName())) {
                        familyList.add(familyBean);
                    }
                    count++;
                }
                //封装房屋情况
                packageHouseInfo(houseStateBean, itemSheet.getRow(++count));
                houseStateBean.setHouseid(houseHoldBean.getId());
                ++count;
                ++count;
                //文字说明
                String otherCaseContent = itemSheet.getRow(++count).getCell(0).toString();
                houseHoldBean.setDesc(otherCaseContent);
                List<PictureBean> pictureBeans = packageImageInfo(itemSheet);

                //将信息全部封装到数据库中
                addToDataBase(houseHoldBean, familyList, houseStateBean, pictureBeans);

            }
        }
    }

    /**
     * 判断是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof Row) {
            return obj == null || "".equals(((Row) obj).getCell(0).toString());
        }
        return obj == null;
    }

    /**
     * 封装图像信息
     *
     * @param itemSheet
     * @return
     */
    public static List<PictureBean> packageImageInfo(HSSFSheet itemSheet) {
        HSSFPatriarch hssfPatriarch = itemSheet.getDrawingPatriarch();
        //每个Sheet对应多个pictureBean
        ArrayList<PictureBean> pictureBeans = new ArrayList<>();
        if (isEmpty(hssfPatriarch)) {
            return pictureBeans;
        }
//        Map<String, PictureData> map = new LinkedHashMap<>();
        List<HSSFShape> shapes = hssfPatriarch.getChildren();
        for (int i = 0; i < shapes.size(); i++) {
            HSSFShape hssfShape = shapes.get(i);
//            HSSFClientAnchor anchor = (HSSFClientAnchor) hssfShape.getAnchor();
            if (hssfShape instanceof HSSFPicture) {
                HSSFPicture pic = (HSSFPicture) hssfShape;
                //获取列编号
//                short col = anchor.getCol2();
                HSSFPictureData pictureData = pic.getPictureData();
//                map.put(getImgKey(i,col),pictureData);//可以存储，但没必要
                byte[] data = pictureData.getData();
                String id = itemSheet.getRow(2).getCell(5).toString();
                //将图片做成Base64并存储
                String base64String = Base64.encodeBase64String(data);
                PictureBean pictureBean = new PictureBean();
                pictureBean.setBase(base64String);
                pictureBean.setMsid(id + "_" + i);
                pictureBean.setHouseid(id);
                pictureBeans.add(pictureBean);
            }
        }
        return pictureBeans;
    }

    /**
     * 封装基本信息
     *
     * @param houseHoldBean
     * @param masterMsg
     */
    public static void packageBaseInfo(HouseHoldBean houseHoldBean, Row masterMsg) {
        houseHoldBean.setName(transToString(masterMsg.getCell(0)));
        houseHoldBean.setSex(transToString(masterMsg.getCell(1)).equals("男") ? 0 : 1);
        houseHoldBean.setNation(transToString(masterMsg.getCell(2)));
        houseHoldBean.setPc(transToString(masterMsg.getCell(3)));
        houseHoldBean.setCulture(transToString(masterMsg.getCell(4)));
        masterMsg.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
        houseHoldBean.setId(transToString(masterMsg.getCell(5)));
        houseHoldBean.setFaith(transToString(masterMsg.getCell(6)));
        String[] gridCode = transToString(masterMsg.getCell(7)).split("\\.");
        houseHoldBean.setGridCode(gridCode[0]);
        masterMsg.getCell(8).setCellType(HSSFCell.CELL_TYPE_STRING);
        houseHoldBean.setTel(transToString(masterMsg.getCell(8)));
        houseHoldBean.setDifficulty(transToString(masterMsg.getCell(9)));
        String[] split = transToString(masterMsg.getCell(10)).split("\\.");
        houseHoldBean.setDisableId(split[0]);
        houseHoldBean.setIsResident(transToString(masterMsg.getCell(11)).equals("是") ? 0 : 1);
        //获取当前年份
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        //获取年龄
        int orange = Integer.parseInt(transToString(masterMsg.getCell(5)).substring(6, 10));
        houseHoldBean.setAge(year - orange + 1);
    }

    /**
     * 封装family成员方法
     *
     * @param familyBean
     * @param masterMsg
     */
    public static void packageFamilyInfo(FamilyBean familyBean, Row masterMsg) {
        String name = transToString(masterMsg.getCell(0));
        if ("".equals(name) || name == null || "null".equals(name)) {
            return;
        }
        familyBean.setName(transToString(masterMsg.getCell(0)));
        familyBean.setRelation(transToString(masterMsg.getCell(1)));
        familyBean.setNation(transToString(masterMsg.getCell(2)));
        familyBean.setPc(transToString(masterMsg.getCell(3)));
        familyBean.setCulture(transToString(masterMsg.getCell(4)));
        masterMsg.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
        familyBean.setId(transToString(masterMsg.getCell(5)));
        familyBean.setWorkAdd(transToString(masterMsg.getCell(6)));
        familyBean.setSex(transToString(masterMsg.getCell(7)).equals("男") ? 0 : 1);
        familyBean.setIsResident(transToString(masterMsg.getCell(8)).equals("是") ? 0 : 1);
        masterMsg.getCell(9).setCellType(HSSFCell.CELL_TYPE_STRING);
        familyBean.setTel(transToString(masterMsg.getCell(9)));
        familyBean.setDifficulty(transToString(masterMsg.getCell(10)));

        //获取当前年份
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        //获取年龄
        int orange = Integer.parseInt(transToString(masterMsg.getCell(5)).substring(6, 10));
        familyBean.setAge(year - orange + 1);

        String[] split = transToString(masterMsg.getCell(11)).split("\\.");
        familyBean.setDisableId(split[0]);
    }

    /**
     * 封装房屋信息
     *
     * @param houseStateBean
     * @param masterMsg
     */
    public static void packageHouseInfo(HouseStateBean houseStateBean, Row masterMsg) {
        masterMsg.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
        houseStateBean.setHomestead(transToString(masterMsg.getCell(1)));
        houseStateBean.setCoverArea(transToString(masterMsg.getCell(3)));
        houseStateBean.setHouseArea(transToString(masterMsg.getCell(5)));
    }

    /**
     * 转化为String类型
     *
     * @param cell
     * @return
     */
    public static String transToString(Cell cell) {
        return cell.toString();
    }

    /**
     * 将封装好的数据插入数据库中
     *
     * @param houseHoldBean
     * @param familyBeanList
     * @param houseStateBean
     * @param pictureBeanList
     * @return
     */
    public static boolean addToDataBase(HouseHoldBean houseHoldBean, List<FamilyBean> familyBeanList,
                                        HouseStateBean houseStateBean, List<PictureBean> pictureBeanList) {

        //1. 插入houseHold表
        boolean houseHoldResult = insertIntoHouseHold(houseHoldBean);
        boolean result = false;
        if (houseHoldResult) {
            //2. 插入family表，插入多条
            boolean familyResult = insertIntoFamily(familyBeanList);
            //3. 插入houseState表
            boolean stateResult = insertIntoFamilyState(houseStateBean);
            //4. 插入picture表，插入多条
            boolean pictureResult = insertIntoPicture(pictureBeanList);
            result = familyResult && stateResult && pictureResult;
        }
        return houseHoldResult && result;
    }

    /**
     * 插入houseHold
     *
     * @param houseHoldBean
     * @return
     */
    public static boolean insertIntoHouseHold(HouseHoldBean houseHoldBean) {
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.insertIntoHouseHold(houseHoldBean);
        session.commit();
        session.close();
        return result;
    }

    /**
     * 插入family表
     *
     * @param familyBeanList
     * @return
     */
    public static boolean insertIntoFamily(List<FamilyBean> familyBeanList) {
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.insertIntoFamily(familyBeanList);
        session.commit();
        session.close();
        return result;
    }

    /**
     * 插入houseState表
     *
     * @param houseStateBean
     * @return
     */
    public static boolean insertIntoFamilyState(HouseStateBean houseStateBean) {
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.insertIntoHouseState(houseStateBean);
        session.commit();
        session.close();
        return result;
    }

    /**
     * 插入picture表
     *
     * @param pictureBeanList
     * @return
     */
    public static boolean insertIntoPicture(List<PictureBean> pictureBeanList) {
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.insertIntoPicture(pictureBeanList);
        session.commit();
        session.close();
        return result;
    }


}
