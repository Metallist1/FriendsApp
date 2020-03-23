package com.example.myfriendsapp.BE;

public class Friend {
    private  String name;
    private  String address;
    private  String phone;
    private  String mail;
    private  String birthday;
    private  String web;
    private  int profilePicture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Friend(String name, String address, String phone, String mail, String birthday, String web, int profilePicture) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.birthday = birthday;
        this.web = web;
        this.profilePicture = profilePicture;
    }
}
