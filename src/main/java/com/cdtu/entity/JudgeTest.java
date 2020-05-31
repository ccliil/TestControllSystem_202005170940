package com.cdtu.entity;

public class JudgeTest {
    private Long jid;
    private String account;
    private Integer answer;
    private  Long majorId;
    private  Integer score;
    private boolean isRight;
    private  String comment;
    private Long uid;
    private Major major;
    private User user;

    public Long getJid() {
        return jid;
    }

    public void setJid(Long jid) {
        this.jid = jid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
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

    public JudgeTest() {
    }

    public JudgeTest(Long jid, String account, Integer answer, Long majorId, Integer score, boolean isRight, String comment, Long uid, Major major, User user) {
        this.jid = jid;
        this.account = account;
        this.answer = answer;
        this.majorId = majorId;
        this.score = score;
        this.isRight = isRight;
        this.comment = comment;
        this.uid = uid;
        this.major = major;
        this.user = user;
    }

    public JudgeTest(Long jid, String account, Integer answer, Long majorId, Integer score, String comment, Long uid) {
        this.jid = jid;
        this.account = account;
        this.answer = answer;
        this.majorId = majorId;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    public JudgeTest(Long jid, String account, Integer answer, Integer score, String comment, Long uid) {
        this.jid = jid;
        this.account = account;
        this.answer = answer;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    public JudgeTest(String account, Integer answer, Long majorId, Integer score, String comment, Long uid) {
        this.account = account;
        this.answer = answer;
        this.majorId = majorId;
        this.score = score;
        this.comment = comment;
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "JudgeTest{" +
                "jid=" + jid +
                ", account='" + account + '\'' +
                ", answer=" + answer +
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
