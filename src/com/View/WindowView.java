package com.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowView extends JFrame {
    WindowViewController controller;
    JButton Search, insert, remove, review, borrowOrBack, history;
    JTextField SearchContent;
    JTextField ReviewContent;
    JTextField AddContent;
    JTextField DelContent;
    JTextField AddNumber, ReviewNumber;
    JTextField GiveBack;
    JTable table;
    DefaultTableModel dataModel;

    Box boxH1, boxH2, boxH3, boxH4, boxH5, boxH6;
    Box boxV1;

    public WindowView(RegisterAndLoginView review, Boolean isManager, String username) {
        if(isManager) setBounds(500,1000,1000,620);  // 设置用户和管理的界面大小
        else setBounds(500,1000,1000,520);
        setLayout(new FlowLayout());
        setTitle("图书界面");
        setLocationRelativeTo(null);  // 打开窗口居中

        init(isManager, username);
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                review.setVisible(true);
            }
        });
    }

    void init(Boolean isManager, String username) {
        table = new JTable();
        controller = new WindowViewController();
        controller.window.setIsManager(isManager);
        controller.window.setUsername(username);
        controller.setWindowView(this);


        dataModel = new DefaultTableModel();  // 设置数据以便后续刷新数据
        table.setModel(dataModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  // 仅可选一行
//        table.getTableHeader().setReorderingAllowed(false);  // 列不可以移动
//        table.getTableHeader().setResizingAllowed(false);


        // 公共部分
        Search = new JButton("搜索");
        borrowOrBack = new JButton("借还");
        history = new JButton("历史");
        SearchContent = new JTextField(20);
        GiveBack = new JTextField(20);

        if (isManager) {
            insert = new JButton("添加");
            remove = new JButton("删除");
            review = new JButton("修改");

            AddContent = new JTextField(20);
            DelContent = new JTextField(20);
            ReviewContent = new JTextField(20);
            GiveBack = new JTextField(20);
            AddNumber = new JTextField(5);
            ReviewNumber = new JTextField(5);

            Search.addActionListener(controller);
            insert.addActionListener(controller);
            remove.addActionListener(controller);
            review.addActionListener(controller);
            borrowOrBack.addActionListener(controller);
            history.addActionListener(controller);

            boxH1 = Box.createHorizontalBox();
            boxH2 = Box.createHorizontalBox();
            boxH3 = Box.createHorizontalBox();
            boxH4 = Box.createHorizontalBox();
            boxH5 = Box.createHorizontalBox();
            boxH6 = Box.createHorizontalBox();
            boxV1 = Box.createVerticalBox();

            // 搜索
            boxH1.add(Search);
            boxH1.add(new JLabel("搜索书名:"));
            boxH1.add(SearchContent);

            // 添加
            boxH2.add(insert);
            boxH2.add(new JLabel("书籍名称:"));
            boxH2.add(AddContent);
            boxH2.add(new JLabel("数量:"));
            boxH2.add(AddNumber);

            // 修改
            boxH3.add(review);
            boxH3.add(new JLabel("书籍名称:"));
            boxH3.add(ReviewContent);
            boxH3.add(new JLabel("数量:"));
            boxH3.add(ReviewNumber);

            // 删除
            boxH4.add(remove);
            boxH4.add(new JLabel("书籍名称:"));
            boxH4.add(DelContent);

            // 借还 历史
            boxH5.add(borrowOrBack);
            boxH5.add(new JLabel("书籍名称:"));
            boxH5.add(GiveBack);
            boxH5.add(history);

            boxV1.add(boxH1);
            boxV1.add(boxH2);
            boxV1.add(boxH3);
            boxV1.add(boxH4);
            boxV1.add(boxH5);
        }
        else {
            Search.addActionListener(controller);
            borrowOrBack.addActionListener(controller);
            history.addActionListener(controller);

            boxH1 = Box.createHorizontalBox();
            boxH2 = Box.createHorizontalBox();
            boxV1 = Box.createVerticalBox();

            // 搜索
            boxH1.add(Search);
            boxH1.add(new JLabel("搜索书名:"));
            boxH1.add(SearchContent);

            // 借还 历史
            boxH2.add(borrowOrBack);
            boxH2.add(new JLabel("书籍名称:"));
            boxH2.add(GiveBack);
            boxH2.add(history);

            boxV1.add(boxH1);
            boxV1.add(boxH2);
        }




        boxV1.add(new JScrollPane(table));
        boxV1.add(new JLabel("使用帮助：1.搜索内容为空则搜索全部书籍2.点击[历史]按钮将查看本人借阅记录3.历史清单中右侧state=0表示借出，1表示归还"));
        add(boxV1);
    }
}