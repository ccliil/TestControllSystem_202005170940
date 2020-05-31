package com.cdtu.entity;


public class TestInfo {
    private Long fid;
    private Long tid;
    private Long uid;
    private Long majorid;
    private String cAnswerList;
    private String jAnswerList;
    private String vAnswerList;
    private Integer score;
    private User user;
    private TestEntity test;
    private String createTime;
    private int delId;

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getMajorid() {
        return majorid;
    }

    public void setMajorid(Long majorid) {
        this.majorid = majorid;
    }

    public String getcAnswerList() {
        return cAnswerList;
    }

    public void setcAnswerList(String cAnswerList) {
        this.cAnswerList = cAnswerList;
    }

    public String getjAnswerList() {
        return jAnswerList;
    }

    public void setjAnswerList(String jAnswerList) {
        this.jAnswerList = jAnswerList;
    }

    public String getvAnswerList() {
        return vAnswerList;
    }

    public void setvAnswerList(String vAnswerList) {
        this.vAnswerList = vAnswerList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int isDelId() {
        return delId;
    }

    public void setDelId(int delId) {
        this.delId = delId;
    }

    public TestInfo(Long tid, Long majorid) {
    }

    public TestInfo() {
    }

    public TestInfo(Long tid, Long uid, Long majorid, String cAnswerList, String jAnswerList, String vAnswerList, Integer score, String createTime, int delId) {
        this.tid = tid;
        this.uid = uid;
        this.majorid = majorid;
        this.cAnswerList = cAnswerList;
        this.jAnswerList = jAnswerList;
        this.vAnswerList = vAnswerList;
        this.score = score;
        this.createTime = createTime;
        this.delId = delId;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "fid=" + fid +
                ", tid=" + tid +
                ", uid=" + uid +
                ", majorid=" + majorid +
                ", cAnswerList='" + cAnswerList + '\'' +
                ", jAnswerList='" + jAnswerList + '\'' +
                ", vAnswerList='" + vAnswerList + '\'' +
                ", score=" + score +
                ", user=" + user +
                ", test=" + test +
                ", createTime='" + createTime + '\'' +
                ", delId=" + delId +
                '}';
    }
}
