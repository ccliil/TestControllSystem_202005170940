package com.cdtu.entity;

public class TestMessage {
    private Long tid;
    private String tname;
    private Integer totalNum;
    private Integer passNum;
    private Double passRate;

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

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
    }

    public TestMessage() {
    }

    public TestMessage(Long tid,String tname, Integer totalNum, Integer passNum, Double passRate) {
        this.tid=tid;
        this.tname = tname;
        this.totalNum = totalNum;
        this.passNum = passNum;
        this.passRate = passRate;
    }

    @Override
    public String toString() {
        return "TestMessage{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", totalNum=" + totalNum +
                ", passNum=" + passNum +
                ", passRate=" + passRate +
                '}';
    }
}
