package com.View;

import com.DAO.UserCRUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// 修改密码界面
public class ResetView extends JFrame {
    JTextField username;
    JPasswordField passwordField1, passwordField2;
    JButton button;

    Box boxH1, boxH2, boxH3, boxH4;
    Box boxV1;

    JRadioButton people;
    JRadioButton manager;
    ButtonGroup bg;

    public ResetView(JButton review) {
        review.setEnabled(false);
        setBounds(200,200,250,170);
        setLayout(new FlowLayout());
        setTitle("修改密码");

        init();
        setLocationRelativeTo(null);  // 打开窗口居中
        setVisible(true);
        setResizable(false);

        // 防止用户多次打开修改密码窗口
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                review.setEnabled(true);
            }
        });
    }
    void init() {
        username = new JTextField(12);
        passwordField1 = new JPasswordField(12);
        passwordField2 = new JPasswordField(12);
        button = new JButton("确认修改");
        people = new JRadioButton("用户");
        manager = new JRadioButton("管理员");
        bg = new ButtonGroup();
        people.setSelected(true);


        boxH1 = Box.createHorizontalBox();
        boxH2 = Box.createHorizontalBox();
        boxH3 = Box.createHorizontalBox();
        boxH4 = Box.createHorizontalBox();
        boxV1 = Box.createVerticalBox();

        boxH1.add(new JLabel("用户名:"));
        boxH1.add(username);
        boxH2.add(new JLabel("旧密码:"));
        boxH2.add(passwordField1);
        boxH3.add(new JLabel("新密码:"));
        boxH3.add(passwordField2);


        boxH4.add(people);
        boxH4.add(manager);
        bg.add(people);
        bg.add(manager);
        boxV1.add(boxH1);
        boxV1.add(boxH2);
        boxV1.add(boxH3);
        boxV1.add(boxH4);
        add(boxV1);
        add(button);

        button.addActionListener(e -> {
            UserCRUD userCRUD = new UserCRUD();
            boolean state = userCRUD.review(username.getText().trim(),
                    new String(passwordField1.getPassword()), new String(passwordField2.getPassword()), manager.isSelected());
            if (state == false) {
                JOptionPane.showMessageDialog(null, "用户名或密码错误，或新密码不能设置为空！", "修改密码提示", JOptionPane.WARNING_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "修改成功！", "修改密码提示", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
