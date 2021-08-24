package com.xhc.view;

import com.xhc.entity.ConditionEntity;
import com.xhc.utils.Constant;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;

public class ConditionQueryResult extends JFrame {

    public ConditionQueryResult() {
        this.setTitle("查询结果");
        dispose();  //关闭当前窗口，不退出整体程序
        this.setSize(1200, 600);
        this.setLocation(200, 100);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void init(List<ConditionEntity> conditionEntities, int totalNum) {
        JPanel jPanel = new JPanel();
        JButton export = new JButton("一键导出");
        //将常量类中的列表引入结果视图中
        String[] conditionMsg = Constant.conditionMsg;
        // 表格所有行数据，需要封装信息
        Object[][] queryResult = new Object[conditionEntities.size()][];
        for (int i = 0; i < conditionEntities.size(); i++) {
            ConditionEntity conditionEntity = conditionEntities.get(i);
            queryResult[i] = new Object[]{conditionEntity.getName(), conditionEntity.getAge(), conditionEntity.getId(),
                    conditionEntity.getSex(), conditionEntity.getTel(), conditionEntity.getCulture(), conditionEntity.getDifficulty(),
                    conditionEntity.getDisableid(), conditionEntity.getIsResident(), conditionEntity.getNation(), conditionEntity.getPc(),
                    conditionEntity.getWorkAdd()};
        }
        // 创建一个表格，指定 表头 和 所有行数据
        JTable masterJTable = adjustTable(new JTable(queryResult, conditionMsg));
        jPanel.setLayout(new GridLayout(3, 1));
        JLabel resultNum = new JLabel();
        resultNum.setText("共查询出总人数为:" + totalNum + "人");
        JScrollPane queryResultPanel = new JScrollPane(masterJTable);
        //添加两个panel
        jPanel.add(resultNum);
        jPanel.add(queryResultPanel);
        this.clickListener(export, conditionEntities);
        jPanel.add(export);
        JScrollPane sp = new JScrollPane(jPanel);
        // 设置内容面板到窗口
        this.setContentPane(sp);

    }

    public JTable adjustTable(JTable table) {
        // 设置行高
        table.setRowHeight(30);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        // 设置横向滚动条
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        return table;
    }

    public void clickListener(JButton export, List<ConditionEntity> conditionEntities) {
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //导出excel表格
                //创建HSSFWorkbook对象
                HSSFWorkbook wb = new HSSFWorkbook();
                //创建HSSFSheet对象
                HSSFSheet sheet = wb.createSheet("sheet1");
                //创建HSSFRow对象
                HSSFRow row = sheet.createRow(0);
                //创建HSSFCell对象
                HSSFCell cell = row.createCell(0);
                //设置单元格的值
                cell.setCellValue("条件筛选一览表");
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
                HSSFRow titleRow = sheet.createRow(1);
                String[] conditionMsg = Constant.conditionMsg;
                //创建表头信息
                for (int i = 0; i < conditionMsg.length; i++) {
                    titleRow.createCell(i).setCellValue(conditionMsg[i]);
                }
                int start = 2;
                for (ConditionEntity condition : conditionEntities) {
                    HSSFRow contentRow = sheet.createRow(start);
                    contentRow.createCell(0).setCellValue(condition.getName());
                    contentRow.createCell(1).setCellValue(condition.getAge());
                    contentRow.createCell(2).setCellValue(condition.getId());
                    String sexValue = condition.getSex() == 0 ? "男" : "女";
                    contentRow.createCell(3).setCellValue(sexValue);
                    contentRow.createCell(4).setCellValue(condition.getTel());
                    contentRow.createCell(5).setCellValue(condition.getCulture());
                    contentRow.createCell(6).setCellValue(condition.getDifficulty());
                    contentRow.createCell(7).setCellValue(condition.getDisableid());
                    String residentValue = condition.getIsResident() == 0 ? "是" : "否";
                    contentRow.createCell(8).setCellValue(residentValue);
                    contentRow.createCell(9).setCellValue(condition.getNation());
                    contentRow.createCell(10).setCellValue(condition.getPc());
                    contentRow.createCell(11).setCellValue(condition.getWorkAdd());
                    start++;
                }
                //输出Excel文件
                FileOutputStream output = null;
                try {
                    output = new FileOutputStream("C:\\Users\\lenovo\\Desktop\\test\\workbook.xls");
                    wb.write(output);
                    output.flush();
                    wb.close();

                } catch (Exception out) {
                    out.printStackTrace();
                }
                new MyDialog(ConditionQueryResult.this).setVisible(true);

            }
        });
    }


}

class MyDialog extends JDialog {
    public MyDialog(JFrame jFrame) {
        super(jFrame, "打印提示");
        Container contentPane = getContentPane();
        contentPane.add(new JLabel("导出完成"));
        setBounds(100, 100, 100, 100);
    }
}
