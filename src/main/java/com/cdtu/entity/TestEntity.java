package com.cdtu.entity;

import java.util.ArrayList;
import java.util.List;

public class TestEntity {
    private Long tid;
    private String tname;
    private Long majorid;
    private Long uid;
    private String createTime;
    private String cidList;
    private String jidList;
    private String vidList;
    private Integer totalScore;
    private Integer isUsed;
    private Major major;
    private User user;
    private List<TestInfo> listInfo=new ArrayList<>();

    public TestEntity(String tname, Long majorid, Long uid, String createTime, String cidList, String jidList, String vidList, Integer totalScore,Integer isUsed) {
        this.tname = tname;
        this.majorid = majorid;
        this.uid = uid;
        this.createTime = createTime;
        this.cidList = cidList;
        this.jidList = jidList;
        this.vidList = vidList;
        this.totalScore = totalScore;
        this.isUsed=isUsed;
    }

    public TestEntity() {
    }

    public TestEntity(Long tid, String tname, Long majorid, Long uid, int totalScore, Integer isUsed) {
        this.tid=tid;
        this.tname=tname;
        this.majorid=majorid;
        this.uid=uid;
        this.totalScore=totalScore;
        this.isUsed=isUsed;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Long getMajorid() {
        return majorid;
    }

    public void setMajorid(Long majorid) {
        this.majorid = majorid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCidList() {
        return cidList;
    }

    public void setCidList(String cidList) {
        this.cidList = cidList;
    }

    public String getJidList() {
        return jidList;
    }

    public void setJidList(String jidList) {
        this.jidList = jidList;
    }

    public String getVidList() {
        return vidList;
    }

    public void setVidList(String vidList) {
        this.vidList = vidList;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TestInfo> getListInfo() {
        return listInfo;
    }

    public void setListInfo(List<TestInfo> listInfo) {
        this.listInfo = listInfo;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", majorid=" + majorid +
                ", uid=" + uid +
                ", createTime='" + createTime + '\'' +
                ", cidList='" + cidList + '\'' +
                ", jidList='" + jidList + '\'' +
                ", vidList='" + vidList + '\'' +
                ", totalScore=" + totalScore +
                ", isUsed=" + isUsed +
                ", major=" + major +
               ", user=" + user +
//                ", listInfo=" + listInfo +
                '}';
    }
}
