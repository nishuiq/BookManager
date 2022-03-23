package com.RegistAndLoginModule;

// 数据存储
public class RegisterAndLogin {
    private String id;
    private String password;
    private Boolean isManager;

    public void setId(String id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setManager(Boolean manager) {
        isManager = manager;
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public Boolean getManager() {
        return isManager;
    }
}
