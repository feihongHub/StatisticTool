package com.xhc.view;

import com.xhc.entity.ConditionEntity;
import com.xhc.entity.FamilyBean;
import com.xhc.entity.HouseHoldBean;
import com.xhc.mapper.IMapper;
import com.xhc.utils.Initial;
import com.xhc.utils.Symbol;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ConditionQuery extends JFrame {
    //七个面板
    JPanel sexPanel, culturePanel, agePanel, disablePanel, residentPanel, jobPanel, faithPanel, buttonPanel;
    //两个按钮
    JButton submit, cancel;
    //七个标签
    JLabel sexLabel, cultureLabel, ageLabel, disableLabel, residentLabel, jobLabel, faithLabel;
    JTextField ageField;     //文本框（显示）
    //编辑组件
    //性别下拉列表
    JComboBox<String> comboBox;
    JComboBox<String> disableBox;
    JComboBox<String> residentBox;
    JComboBox<String> jobBox;
    JComboBox<String> faithBox;


    public ConditionQuery() {
        sexPanel = new JPanel();
        culturePanel = new JPanel();
        agePanel = new JPanel();
        disablePanel = new JPanel();
        residentPanel = new JPanel();
        jobPanel = new JPanel();
        faithPanel = new JPanel();
        buttonPanel = new JPanel();
        //提交按钮
        submit = new JButton("查询");
        cancel = new JButton("取消");
        //标签
        sexLabel = new JLabel("性别:");
        comboBox = new JComboBox<>();
        comboBox.addItem("---选择类型---");
        comboBox.addItem("男");
        comboBox.addItem("女");
        //文化多选框
        cultureLabel = new JLabel("文化程度:");
        culturePanel.add(cultureLabel);
        JCheckBox checkBox01 = new JCheckBox("小学以下");
        JCheckBox checkBox02 = new JCheckBox("小学");
        JCheckBox checkBox03 = new JCheckBox("初中");
        JCheckBox checkBox04 = new JCheckBox("高中");
        JCheckBox checkBox05 = new JCheckBox("中专");
        JCheckBox checkBox06 = new JCheckBox("大专");
        JCheckBox checkBox07 = new JCheckBox("大学");
        JCheckBox checkBox08 = new JCheckBox("研究生");
        culturePanel.add(checkBox01);
        culturePanel.add(checkBox02);
        culturePanel.add(checkBox03);
        culturePanel.add(checkBox04);
        culturePanel.add(checkBox05);
        culturePanel.add(checkBox06);
        culturePanel.add(checkBox07);
        culturePanel.add(checkBox08);

        //年龄文本框
        ageLabel = new JLabel("填写年龄(支持>,<的写法):");
        ageField = new JTextField(10);
        //残疾单选框
        disableLabel = new JLabel("是否残疾:");
        disableBox = new JComboBox<>();
        disableBox.addItem("---选择类型---");
        disableBox.addItem("是");
        disableBox.addItem("否");

        //常住单选框
        residentLabel = new JLabel("是否常住:");
        residentBox = new JComboBox<>();
        residentBox.addItem("---选择类型---");
        residentBox.addItem("是");
        residentBox.addItem("否");

        //工作单选框
        jobLabel = new JLabel("有无工作地址:");
        jobBox = new JComboBox<>();
        jobBox.addItem("---选择类型---");
        jobBox.addItem("是");
        jobBox.addItem("否");

        //信仰单选框
        faithLabel = new JLabel("有无信仰:");
        faithBox = new JComboBox<>();
        faithBox.addItem("---选择类型---");
        faithBox.addItem("是");
        faithBox.addItem("否");

        //设置布局模式，八行一列布局
        this.setLayout(new GridLayout(8, 1));
        //添加性别Panel
        sexPanel.add(sexLabel);
        sexPanel.add(comboBox);
        //添加年龄Panel
        agePanel.add(ageLabel);
        agePanel.add(ageField);
        //添加残疾Panel
        disablePanel.add(disableLabel);
        disablePanel.add(disableBox);
        //添加常驻Panel
        residentPanel.add(residentLabel);
        residentPanel.add(residentBox);
        //添加工作Panel
        jobPanel.add(jobLabel);
        jobPanel.add(jobBox);
        //添加信念Panel
        faithPanel.add(faithLabel);
        faithPanel.add(faithBox);
        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        this.add(sexPanel);
        this.add(culturePanel);
        this.add(agePanel);
        this.add(disablePanel);
        this.add(residentPanel);
        this.add(jobPanel);
        this.add(faithPanel);
        this.add(buttonPanel);
        this.setTitle("村民卡信息管里系统");
        this.setSize(800, 400);
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    //开启视图
    public static void start() {
        ConditionQuery conditionQuery = new ConditionQuery();
        conditionQuery.addActionListener(conditionQuery.submit, conditionQuery.comboBox, conditionQuery.ageField,
                conditionQuery.disableBox, conditionQuery.jobBox, conditionQuery.faithBox);
    }

    //添加点击事件
    public void addActionListener(JButton button, JComboBox<String> comboBox, JTextField ageField,
                                  JComboBox<String> disableBox, JComboBox<String> jobBox, JComboBox<String> faithBox) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //进行输入内容的查询
                Map<String, Object> inputMsg = new HashMap<String, Object>();
//                String sexValue = comboBox.getSelectedItem().toString() == "0" ? "男" : "女";
                inputMsg.put("sex", comboBox.getSelectedItem().toString());
                //获取复选框的值
                String cultureInfo = "";
                for (Component item : culturePanel.getComponents()) {
                    if (item instanceof JCheckBox) {
                        if (((JCheckBox) item).isSelected()) {
//                            cultureInfo += "\'" + ((JCheckBox) item).getText() + "\'" + ",";
                            cultureInfo += ((JCheckBox) item).getText() + ",";
                        }
                    }
                }
                int length = cultureInfo.length();
                if (length > 0) {
                    String temp = cultureInfo.substring(0, length - 1);
                    String[] split = temp.split(",");
                    List<String> cultureInfoList = Arrays.asList(split);
                    inputMsg.put("culture", cultureInfoList);
                } else {
                    inputMsg.put("culture", "");
                }
                String ageContent = ageField.getText();
                if (("").equals(ageContent) || ageContent == null) {
                    inputMsg.put("age", "unknow");
                } else {
                    //开始判定内部是否有特殊符号
                    if (ageContent.contains(">=")) {
                        inputMsg.put("age", Symbol.MOREEQU.ordinal() + "#" + ageContent.split("=")[1]);
                    } else if (ageContent.contains("<=")) {
                        inputMsg.put("age", Symbol.SMALLEQU.ordinal() + "#" + ageContent.split("=")[1]);
                    } else if (ageContent.contains(">")) {
                        inputMsg.put("age", Symbol.MORE.ordinal() + "#" + ageContent.split(">")[1]);
                    } else if (ageContent.contains("<")) {
                        inputMsg.put("age", Symbol.SMALL.ordinal() + "#" + ageContent.split("<")[1]);
                    } else {
                        inputMsg.put("age", Symbol.EQULE.ordinal() + "#" + ageContent.split("=")[1]);
                    }
                }
                inputMsg.put("disable", disableBox.getSelectedItem().toString());
//                String residentValue = residentBox.getSelectedItem().toString() == "0" ? "是" : "否";
                inputMsg.put("resident", residentBox.getSelectedItem().toString());
                inputMsg.put("job", jobBox.getSelectedItem().toString());
                inputMsg.put("faith", faithBox.getSelectedItem().toString());
                //将封装好的Map用于查询
                SqlSession session = Initial.getSession();
                IMapper mapper = session.getMapper(IMapper.class);
                //多条件查询，查询出户主表全部数据
                List<HouseHoldBean> houseHoldBeans = mapper.queryMultiHold(inputMsg);
                //多条件查询，查询出关联人全部数据
                List<FamilyBean> familyBeans = mapper.queryMultiFamily(inputMsg);
                session.close();
                //进行封装展示
                List<ConditionEntity> conditionEntities = new ArrayList<>();
                for (HouseHoldBean houseHold : houseHoldBeans) {
                    //封装household
                    ConditionEntity conditionEntity = new ConditionEntity();
                    conditionEntity.setName(houseHold.getName());
                    conditionEntity.setId(houseHold.getId());
                    conditionEntity.setSex(houseHold.getSex());
                    conditionEntity.setNation(houseHold.getNation());
                    conditionEntity.setPc(houseHold.getPc());
                    conditionEntity.setCulture(houseHold.getCulture());
                    conditionEntity.setTel(houseHold.getTel());
                    conditionEntity.setDifficulty(houseHold.getDifficulty());
                    conditionEntity.setDisableid(houseHold.getDisableId());
                    conditionEntity.setIsResident(houseHold.getIsResident());
                    conditionEntity.setWorkAdd(houseHold.getWorkAdd());
                    conditionEntity.setAge(houseHold.getAge());

                    conditionEntities.add(conditionEntity);

                }
                for (FamilyBean family : familyBeans) {
                    //封装family
                    ConditionEntity conditionEntity = new ConditionEntity();
                    conditionEntity.setName(family.getName());
                    conditionEntity.setId(family.getId());
                    conditionEntity.setSex(family.getSex());
                    conditionEntity.setNation(family.getNation());
                    conditionEntity.setPc(family.getPc());
                    conditionEntity.setCulture(family.getCulture());
                    conditionEntity.setTel(family.getTel());
                    conditionEntity.setDifficulty(family.getDifficulty());
                    conditionEntity.setDisableid(family.getDisableId());
                    conditionEntity.setIsResident(family.getIsResident());
                    conditionEntity.setWorkAdd(family.getWorkAdd());
                    conditionEntity.setAge(family.getAge());
                    conditionEntities.add(conditionEntity);
                }
                //共有结果人数
                int resultNum = houseHoldBeans.size() + familyBeans.size();
                //展示所有的视图
                new ConditionQueryResult().init(conditionEntities, resultNum);

            }
        });
    }
}
