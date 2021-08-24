package com.xhc.view;

import com.xhc.entity.FamilyBean;
import com.xhc.entity.HouseHoldBean;
import com.xhc.entity.HouseStateBean;
import com.xhc.entity.PictureBean;
import com.xhc.mapper.IMapper;
import com.xhc.utils.Constant;
import com.xhc.utils.Initial;
import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.session.SqlSession;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

//单人查询图形化界面
public class IndividualQuery extends JFrame {
    JPanel mb1, mb2, mb3;//三个面板
    JButton submit, cancel;    //两个按钮
    JLabel userNameLabel, userIdLabel;     //两个标签
    JTextField userName;     //文本框（显示）
    JTextField userId;     //文本框（显示）

    public IndividualQuery() {
        mb1 = new JPanel();
        mb2 = new JPanel();
        mb3 = new JPanel();

        submit = new JButton("查询");
        cancel = new JButton("取消");
        userNameLabel = new JLabel("户主姓名");
        userIdLabel = new JLabel("户主身份证");
        userName = new JTextField(10);
        userId = new JTextField(20);

        this.setLayout(new GridLayout(3, 1));//三行一列布局
        mb1.add(userNameLabel);
        mb1.add(userName);
        mb2.add(userIdLabel);
        mb2.add(userId);
        mb3.add(submit);
        mb3.add(cancel);
        this.add(mb1);
        this.add(mb2);
        this.add(mb3);

        this.setTitle("村民卡信息管里系统");
        this.setSize(800, 400);
        this.setLocation(200, 200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public static void addActionListener(JButton saveButton, JTextField userName, JTextField userId) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("查询".equals(saveButton.getText())) {
                    //通过搜索栏中所填写的内容进行检索。
                    //进行校验
                    boolean result = false;
                    if(!"".equals(userId.getText())){
                        result = searchResult(userName.getText(), userId.getText());
                    }
                    if (!result) {
                        //返回为null,查无此人
                        JOptionPane.showMessageDialog(null, "查询失败，请输入正确的姓名及有效身份证!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "暂时不开发！");
                }
            }
        });
    }

    //条件查询方法
    public static boolean searchResult(String masterName, String masterId) {
        boolean result = false;
        //查询household数据库
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        HouseHoldBean houseHoldBean = mapper.queryOnehold(masterName, masterId);
        List<FamilyBean> familyBeans;
        HouseStateBean houseStateBean;
        List<PictureBean> pictureBeans;
        //当查询结果为不空时候继续
        if (Objects.nonNull(houseHoldBean)) {
            familyBeans = mapper.queryOneFamily(houseHoldBean.getId());
            houseStateBean = mapper.queryOneState(houseHoldBean.getId());
            pictureBeans = mapper.queryOnePicture(houseHoldBean.getId());
            //转化数据用于显示
            ResultMsg resultMsg = new ResultMsg();
            resultMsg.init(houseHoldBean, familyBeans, houseStateBean, pictureBeans);
            result = true;
        }

        session.close();
        return result;
    }

    //启动函数
    public static void start() {
        IndividualQuery visualShow = new IndividualQuery();
        addActionListener(visualShow.submit, visualShow.userName, visualShow.userId);
        addActionListener(visualShow.cancel, visualShow.userName, visualShow.userId);
    }

}

class ResultMsg extends JFrame {
    //初始化JPanel，将表格扔进去
    public void init(HouseHoldBean houseHoldBean, List<FamilyBean> familyBeanList, HouseStateBean houseStateBean, List<PictureBean> pictureBeanList) {
        JPanel jPanel = new JPanel();
        // 表头（列名）
        String[] masterMsg = Constant.masterMsg;
        String[] familyMsg = Constant.familyMsg;
        String[] imgMsg = Constant.imgMsg;
        String[] houseStateMsg = Constant.houseStateMsg;
        String[] otherCaseMsg = Constant.otherCaseMsg;
        // 表格所有行数据，需要封装信息
        String sexValue = houseHoldBean.getSex() == 0 ? "男" : "女";
        String residentValue = houseHoldBean.getIsResident() == 0 ? "是" : "否";
        Object[][] masterRowData = {
                {houseHoldBean.getName(), sexValue, houseHoldBean.getNation(),
                        houseHoldBean.getPc(), houseHoldBean.getCulture(), houseHoldBean.getId(),
                        houseHoldBean.getFaith(), houseHoldBean.getGridCode(), houseHoldBean.getTel(),
                        houseHoldBean.getDifficulty(), houseHoldBean.getDisableId(), residentValue,
                        houseHoldBean.getHomeAdd(), houseHoldBean.getWorkAdd()}
        };
        //家庭基本信息，由于家人有很多，所以需要封装,当没有人填写的是偶
        ArrayList<Object> familyList = new ArrayList<>();
        for (int i = 0; i < familyBeanList.size(); i++) {
            FamilyBean familyBean = familyBeanList.get(i);
            if ("".equals(familyBean.getName())) {
                continue;
            }
            familyList.add(familyBean.toFamilyString(familyBean));
        }
        Object[][] familyRowData = new Object[familyList.size()][];
        for (int i = 0; i < familyList.size(); i++) {
            familyRowData[i] = familyList.get(i).toString().split(",");
        }
        //图片基本信息
        Object[][] imgRowData = new Object[pictureBeanList.size()][];
        if (pictureBeanList.size() == 0) {
            imgRowData = new Object[][]{{"无图片"}};
        } else {
            //将图像显示
            for (int i = 0; i < pictureBeanList.size(); i++) {
                //需要将数据库中的base64码读取成为图片
                byte[] bytes = Base64.decodeBase64(pictureBeanList.get(i).getBase());

                ImageIcon imageIcon = new ImageIcon(bytes);
//                imageIcon.setImage(imageIcon.getImage().getScaledInstance(200,50,Image.SCALE_DEFAULT));
                imgRowData[i] = new Object[]{imageIcon};
            }
        }
        //房屋基本信息
        Object[][] houseStageData = {{houseStateBean.getHomestead(), houseStateBean.getCoverArea(),
                houseStateBean.getHouseArea()}};
        Object[][] otherRowData = {{houseHoldBean.getDesc()}};

        // 创建一个表格，指定 表头 和 所有行数据
        JTable masterJTable = adjustTable(new JTable(masterRowData, masterMsg));
        JTable familyJTable = adjustTable(new JTable(familyRowData, familyMsg));
        //图片的JTable特殊
        DefaultTableModel model = new DefaultTableModel(imgRowData, imgMsg);
        JTable table = new JTable(model) {
            public Class getColumnClass(int column) {
                return (column == 0) ? Icon.class : Object.class;
            }
        };
        JTable imgJTable = adjustTable(table);
        fitTableColumns(imgJTable);
        imgJTable.setRowHeight(300);
        JTable houseJTable = adjustTable(new JTable(houseStageData, houseStateMsg));
        JTable otherJTable = adjustTable(new JTable(otherRowData, otherCaseMsg));
        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane masterScrollPane = new JScrollPane(masterJTable);
        JScrollPane familyScrollPane = new JScrollPane(familyJTable);
        JScrollPane imgScrollPane = new JScrollPane(imgJTable);
        JScrollPane houseScrollPane = new JScrollPane(houseJTable);
        JScrollPane otherScrollPane = new JScrollPane(otherJTable);
        // 添加 滚动面板 到 内容面板
        jPanel.setLayout(new GridLayout(5, 1));
        jPanel.add(masterScrollPane);
        jPanel.add(familyScrollPane);
        jPanel.add(imgScrollPane);
        jPanel.add(houseScrollPane);
        jPanel.add(otherScrollPane);
        JScrollPane sp = new JScrollPane(jPanel);
        // 设置内容面板到窗口
        this.setContentPane(sp);
    }

    public ResultMsg() {
        this.setTitle("查询结果");
        dispose();  //关闭当前窗口，不退出整体程序
        this.setSize(1200, 600);
        this.setLocation(200, 100);
        this.setResizable(false);
        this.setVisible(true);
    }

    public JTable adjustTable(JTable table) {
        // 设置行高
        table.setRowHeight(30);
        // 第一列列宽设置为40
//        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        // 设置横向滚动条
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return table;
    }

    public void fitTableColumns(JTable myTable) { // 設置table的列寬隨內容調整
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();
        Enumeration columns = myTable.getColumnModel().getColumns();
        while (columns.hasMoreElements()) {
            TableColumn column = (TableColumn) columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());

            int width = (int) myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize().getWidth();
            for (int row = 0; row < rowCount; row++) {
                int preferedWidth = (int) myTable.getCellRenderer(row, col)
                        .getTableCellRendererComponent(myTable, myTable.getValueAt(row, col), false, false, row, col)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width + myTable.getIntercellSpacing().width);
        }

    }
}
