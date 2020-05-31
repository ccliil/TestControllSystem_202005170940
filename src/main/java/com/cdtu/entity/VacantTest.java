package com.cdtu.entity;

public class VacantTest {
    private Long vid;
    private String account;
    private String answer;
    private Long majorId;
    private Integer score;
    private boolean isRight;
    private String comment;
    private Long uid;
    private Major major;
    private User user;

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public VacantTest() {
    }

    public VacantTest(Long vid, String account, String answer, Long majorId, Integer score, String comment, Long uid) {
        this.vid = vid;
        this.account = account;
        this.answer = answer;
        this.majorId = majorId;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    public VacantTest(Long vid, String account, String answer, Integer score, String comment, Long uid) {
        this.vid = vid;
        this.account = account;
        this.answer = answer;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    public VacantTest(String account, String answer, Long majorId, Integer score, String comment, Long uid) {
        this.account = account;
        this.answer = answer;
        this.majorId = majorId;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "VacantTest{" +
                "vid=" + vid +
                ", account='" + account + '\'' +
                ", answer='" + answer + '\'' +
                ", majorId=" + majorId +
                ", score=" + score +
                ", isRight=" + isRight +
                ", comment='" + comment + '\'' +
                ", uid=" + uid +
                ", major=" + major +
                ", user=" + user +
                '}';
    }
}
