package com.WindowMoudle;

public class Window {
    private String title;
    private String cnt;
    private String username;
    private Boolean isManager;

    public void setCnt(String cnt) {
        this.cnt = cnt;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }
    public String getUsername() {
        return username;
    }
    public Boolean getIsManager() {
        return isManager;
    }
    public String getTitle() {
        return title;
    }
    public String getCnt() {
        return cnt;
    }
}