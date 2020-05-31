package com.cdtu.entity;

public class User {
    private Long id;
    private Long majorid;
    private String username ;
    private String password;
    private String tel;
    private String name;
    private  String identify;
    private Major major;
    private String textpass;//未被加密的密码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMajorid() {
        return majorid;
    }

    public void setMajorid(Long majorid) {
        this.majorid = majorid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getTextpass() {
        return textpass;
    }

    public void setTextpass(String textpass) {
        this.textpass = textpass;
    }

    public User(String username, String password, String tel, String name, String identify, Long majorid) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.identify = identify;
        this.majorid = majorid;
    }
    public User(String username, String password, String tel, String name, String identify, Long majorid,String textpass) {
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.identify = identify;
        this.majorid = majorid;
        this.textpass=textpass;
    }
    public User(Long id, String username, String password, String tel, String name, String identify, Long majorid) {
        this.id = id;
        this.majorid = majorid;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.identify = identify;

    }
    public User(Long id, String username, String password, String tel, String name, String identify, Long majorid,String textpass) {
        this.id = id;
        this.majorid = majorid;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;
        this.identify = identify;
        this.textpass=textpass;
    }
    public User(Long id, String username, String password, String tel, String name, String textpass) {
        this.id = id;

        this.username = username;
        this.password = password;
        this.tel = tel;
        this.name = name;

        this.textpass=textpass;
    }
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", majorid=" + majorid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", name='" + name + '\'' +
                ", identify='" + identify + '\'' +
                ", major=" + major +
                ", textpass='" + textpass + '\'' +
                '}';
    }
}
