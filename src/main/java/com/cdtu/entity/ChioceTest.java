package com.cdtu.entity;

import javax.print.DocFlavor;

public class ChioceTest {
    private Long cid;
    private  String account;
    private  String Aitem;
    private  String  Bitem;
    private String Citem;
    private String Ditem;
    private String  answer;
    private Integer score;
    private Long majorid;
    private boolean isRight;
    private String comment;
    private Long uid;
    private User user;
    private Major major;

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAitem() {
        return Aitem;
    }

    public void setAitem(String aitem) {
        Aitem = aitem;
    }

    public String getBitem() {
        return Bitem;
    }

    public void setBitem(String bitem) {
        Bitem = bitem;
    }

    public String getCitem() {
        return Citem;
    }

    public void setCitem(String citem) {
        Citem = citem;
    }

    public String getDitem() {
        return Ditem;
    }

    public void setDitem(String ditem) {
        Ditem = ditem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getMajorid() {
        return majorid;
    }

    public void setMajorid(Long majorid) {
        this.majorid = majorid;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public ChioceTest() {
    }

    public ChioceTest(Long cid, String account, String a, String b, String c, String d, String answer, Integer score, Long majorid, boolean isRight, String comment, Long uid) {
        this.cid = cid;
        this.account = account;
        Aitem = a;
        Bitem = b;
        Citem = c;
        Ditem= d;
        this.answer = answer;
        this.score = score;
        this.majorid = majorid;
        this.isRight = isRight;
        this.comment = comment;
        this.uid = uid;
    }

    public ChioceTest(Long cid, String account, String aitem, String bitem, String citem, String ditem, String answer, Integer score, Long majorid, Long uid) {
        this.cid = cid;
        this.account = account;
        Aitem = aitem;
        Bitem = bitem;
        Citem = citem;
        Ditem = ditem;
        this.answer = answer;
        this.score = score;
        this.majorid = majorid;
        this.uid = uid;
    }

    public ChioceTest(Long cid, String account, String a, String b, String c, String d, String answer, Integer score, Long majorid, boolean isRight, String comment, Long uid, User user, Major major) {
        this.cid = cid;
        this.account = account;
        Aitem = a;
        Bitem = b;
        Citem = c;
        Ditem = d;
        this.answer = answer;
        this.score = score;
        this.majorid = majorid;
        this.isRight = isRight;
        this.comment = comment;
        this.uid = uid;
        this.user = user;
        this.major = major;
    }

    public ChioceTest(String account, String aitem, String bitem, String citem, String ditem, String answer, Integer score, Long majorid, boolean isRight, String comment, Long uid) {
        this.account = account;
        Aitem = aitem;
        Bitem = bitem;
        Citem = citem;
        Ditem = ditem;
        this.answer = answer;
        this.score = score;
        this.majorid = majorid;
        this.isRight = isRight;
        this.comment = comment;
        this.uid = uid;
    }

    public ChioceTest(Long cid, String account, String aitem, String bitem, String citem, String ditem, String answer, Integer score, Long uid) {
        this.cid = cid;
        this.account = account;
        Aitem = aitem;
        Bitem = bitem;
        Citem = citem;
        Ditem = ditem;
        this.answer = answer;
        this.score = score;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ChioceTest{" +
                "cid=" + cid +
                ", account='" + account + '\'' +
                ", A='" + Aitem + '\'' +
                ", B='" + Bitem+ '\'' +
                ", C='" + Citem + '\'' +
                ", D='" + Ditem + '\'' +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", majorId=" + majorid +
                ", isRight=" + isRight +
                ", comment='" + comment + '\'' +
                ", uid=" + uid +
                ", user=" + user +
                ", major=" + major +
                '}';
    }
}
