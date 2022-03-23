package com.View;

import javax.swing.*;
import java.awt.*;

public class RegisterAndLoginView extends JFrame {
    JButton reg, log, review;
    JRadioButton people, manager;
    ButtonGroup bg;
    JTextField username;
    JPasswordField passwordField;

    Box boxH1, boxH2, boxH3, boxH4;
    Box boxV1;

    RegisterAndLoginViewController controller;

    public RegisterAndLoginView() {
        setBounds(500,500,310,190);
        setLayout(new FlowLayout());
        setTitle("图书管理系统");
        setLocationRelativeTo(null);  // 打开窗口居中

        init();

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init() {
        controller = new RegisterAndLoginViewController();
        controller.setWindowView(this);

        username = new JTextField(14);
        passwordField = new JPasswordField(12);
        bg = new ButtonGroup();
        reg = new JButton("注册");
        log = new JButton("登录");
        review = new JButton("修改密码");
        people = new JRadioButton("用户");
        manager = new JRadioButton("管理员");
        people.setSelected(true);

        reg.addActionListener(controller);
        log.addActionListener(controller);
        review.addActionListener(controller);

        boxH1 = Box.createHorizontalBox();  // 行盒
        boxH2 = Box.createHorizontalBox();
        boxH3 = Box.createHorizontalBox();
        boxH4 = Box.createHorizontalBox();

        boxH1.add(new JLabel("用户名："));
        boxH1.add(username);
        boxH2.add(new JLabel("   密码："));
        boxH2.add(passwordField);
        boxH3.add(reg);
        boxH3.add(log);
        boxH3.add(review);
        bg.add(people);  // 二选一
        bg.add(manager);
        boxH4.add(people);
        boxH4.add(manager);

        username.addActionListener(controller);
        passwordField.addActionListener(controller);

        add(new JLabel("登录/注册界面")); //TODO:修改字体样式
        boxV1 = Box.createVerticalBox();  // 列
        boxV1.add(boxH1);
        boxV1.add(boxH2);
        boxV1.add(boxH3);
        boxV1.add(boxH4);

        add(boxV1);
    }
}
