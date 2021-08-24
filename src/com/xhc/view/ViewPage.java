package com.xhc.view;

import com.xhc.utils.DeleteData;
import com.xhc.utils.InputMsg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ViewPage {
    public static JFrame common(JFrame jf){
        // 1.设置窗体大小和标题
        jf.setPreferredSize(new Dimension(700, 700));
        // 2.设置关闭窗口就是关闭程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 最精准的布局模式空布局
        jf.setLayout(null);
        //菜单栏
        //新建一个菜单条
        JMenuBar jb = new JMenuBar();
        jf.add(jb);
        jb.setBounds(0, 40, 690, 30);
        jb.setBackground(Color.decode("#65991a"));
        // 新建一个菜单选项
        JMenu jmenu = new JMenu("一键导入");
        jmenu.setPreferredSize(new Dimension(100, 30));
        jmenu.setForeground(Color.white);
        jb.add(jmenu);
        // 新建一个菜单项
        JMenu jmenu0 = new JMenu("单人查询");
        jmenu0.setPreferredSize(new Dimension(100, 30));
        jmenu0.setForeground(Color.white);
        jb.add(jmenu0);

        // 新建一个菜单项
        JMenu jmenu1 = new JMenu("条件查询");
        jmenu1.setForeground(Color.white);
        jmenu1.setPreferredSize(new Dimension(100, 30));
        jb.add(jmenu1);

        //添加一键导入事件
        jmenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    //先删除
                    DeleteData.deleteHouseHold();
                    DeleteData.deleteFamily();
                    DeleteData.deleteState();
                    DeleteData.deletePicture();
                    //再读取
                    InputMsg.packageExcelToDataBase("C:\\Users\\lenovo\\Desktop\\test");
                    //提示框
                    new MyDialog2(jf).setVisible(true);
                } catch (IOException msg) {
                    msg.printStackTrace();
                }
            }
        });
        //添加单人查询事件
        jmenu0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //展示单人查询的界面
                IndividualQuery.start();
            }
        });
        //添加单人查询事件
        jmenu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //展开条件查询界面
                ConditionQuery.start();

            }
        });
        jf.pack();
        jf.setVisible(true);
        return jf;
    }
}
class MyDialog2 extends JDialog{
    public MyDialog2(JFrame jFrame){
        super(jFrame,"导入提示");
        Container contentPane = getContentPane();
        contentPane.add(new JLabel("导入完成"));
        setBounds(100,100,100,100);
    }
}