package com.cdtu.entity;

public class Time {
    private Long sid;
    private Long uid;
    private String startTime;
    private String endTime;
    private String createTime;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Time() {
    }

    public Time(Long uid, String startTime, String endTime, String createTime) {
        this.uid = uid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Time{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
