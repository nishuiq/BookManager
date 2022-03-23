package com.View;

import com.RegistAndLoginModule.RegisterAndLogin;
import com.RegistAndLoginModule.RegisterAndLoginHandle;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterAndLoginViewController implements ActionListener {
    RegisterAndLoginView registerAndLoginView;
    RegisterAndLogin registerAndLogin;

    public RegisterAndLoginViewController() { registerAndLogin = new RegisterAndLogin(); }

    public void setWindowView(RegisterAndLoginView registerAndLoginView) { this.registerAndLoginView = registerAndLoginView; }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("注册")) {
            if (registerAndLoginView.manager.isSelected()) {
                JOptionPane.showMessageDialog(null, "请联系管理员进行注册！", "注册提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            registerAndLogin.setId(registerAndLoginView.username.getText());
            registerAndLogin.setManager(registerAndLoginView.manager.isSelected());
            char []pw = registerAndLoginView.passwordField.getPassword();
            registerAndLogin.setPassword(new String(pw));

            RegisterAndLoginHandle registerAndLoginHandle = new RegisterAndLoginHandle();
            boolean state = registerAndLoginHandle.writeRegisterModel(registerAndLogin);
            if (state) {
                JOptionPane.showMessageDialog(null, "注册成功！!", "注册提示" , JOptionPane.PLAIN_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "用户名已存在！或用户名或密码为空", "注册提示" , JOptionPane.WARNING_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("登录")) {
            registerAndLogin.setId(registerAndLoginView.username.getText());
            registerAndLogin.setManager(registerAndLoginView.manager.isSelected());
            char []pw = registerAndLoginView.passwordField.getPassword();
            registerAndLogin.setPassword(new String(pw));

            RegisterAndLoginHandle registerAndLoginHandle = new RegisterAndLoginHandle();
            boolean state = registerAndLoginHandle.queryVerify(registerAndLogin);
            if (state == true) {
                System.out.println("登录成功");
                registerAndLoginView.setVisible(false);
                new WindowView(registerAndLoginView, registerAndLogin.getManager(), registerAndLogin.getId());
            }
            else JOptionPane.showMessageDialog(null, "用户名或密码错误！", "登录提示" , JOptionPane.WARNING_MESSAGE);
        }
        else if (e.getActionCommand().equals("修改密码")) {
            ResetView resetView = new ResetView(registerAndLoginView.review);
        }
    }
}
