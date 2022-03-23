package com.RegistAndLoginModule;

import com.DAO.UserCRUD;

// 处理注册和登录的两个功能验证媒介
public class RegisterAndLoginHandle {
    UserCRUD userCRUD;
    public RegisterAndLoginHandle() {
        userCRUD = new UserCRUD();
    }
    public boolean writeRegisterModel(RegisterAndLogin registerAndLogin) {
        if (registerAndLogin.getId().length() == 0 || registerAndLogin.getPassword().length() == 0) return false;
        boolean isSuccess = userCRUD.insert(registerAndLogin.getId().trim(), registerAndLogin.getPassword(), registerAndLogin.getManager());
        return isSuccess;
    }
    public boolean queryVerify(RegisterAndLogin registerAndLogin) {
        if (registerAndLogin.getId().length() == 0 || registerAndLogin.getPassword().length() == 0) return false;
        boolean isSuccess = userCRUD.match(registerAndLogin.getId().trim(), registerAndLogin.getPassword().trim(), registerAndLogin.getManager());
        return isSuccess;
    }
}