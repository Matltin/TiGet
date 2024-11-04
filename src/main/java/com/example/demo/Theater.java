package com.example.demo;

public class Theater {
    private int id;
    private String title;
    private int screenCount;
    private String phoneNumber;
    private String location;

    public Theater (int id, String title, int screenCount, String phoneNumber, String location) {
        this.id = id;
        this.title = title;
        this.screenCount = screenCount;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScreenCount() {
        return screenCount;
    }

    public void setScreenCount(int screenCount) {
        this.screenCount = screenCount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
