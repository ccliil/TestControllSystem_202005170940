package com.cdtu.entity;

import org.springframework.web.multipart.MultipartFile;

public class Material {
    private Long id;
    private Long uId;
    private Long majorId;
    private String dataName;
    private String location;
    private String createTime;
    private String title;
    private String remark;
    private Major major;
    private User user;
    private MultipartFile file;
    private Integer isLoad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
    public void setIsLoad(Integer isLoad){
        this.isLoad=isLoad;
    }
    public Integer getIsLoad(){
        return isLoad;
    }

    public Material() {
    }

    public Material(Long id, Long uId, Long majorId, String dataName, String location, String createTime, String title, String remark) {
        this.id = id;
        this.uId = uId;
        this.majorId = majorId;
        this.dataName = dataName;
        this.location = location;
        this.createTime = createTime;
        this.title = title;
        this.remark = remark;
    }

    public Material(Long uId, Long majorId, String dataName, String location, String createTime, String title, String remark,Integer isLoad) {
        this.uId = uId;
        this.majorId = majorId;
        this.dataName = dataName;
        this.location = location;
        this.createTime = createTime;
        this.title = title;
        this.remark = remark;
        this.isLoad=isLoad;
    }

    public Material(Long id, Long uId, Long majorId, String dataName, String title, String remark,Integer isLoad) {
        this.id = id;
        this.uId = uId;
        this.majorId = majorId;
        this.dataName = dataName;
        this.title = title;
        this.remark = remark;
        this.isLoad=isLoad;
    }
    public Material(Long id, Long uId, String dataName, String title, String remark,Integer isLoad) {
        this.id = id;
        this.uId = uId;
        this.dataName = dataName;
        this.title = title;
        this.remark = remark;
        this.isLoad=isLoad;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", uId=" + uId +
                ", majorId=" + majorId +
                ", dataName='" + dataName + '\'' +
                ", location='" + location + '\'' +
                ", createTime='" + createTime + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", major=" + major +
                ", user=" + user +
                ", file=" + file +
                ", isLoad=" + isLoad +
                '}';
    }
}
