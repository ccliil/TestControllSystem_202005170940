package com.cdtu.entity;

public class VistiedVideo {
    private Long id;
    private Long uid;
    private String video;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public VistiedVideo() {
    }

    public VistiedVideo(Long id, Long uid, String video) {
        this.id = id;
        this.uid = uid;
        this.video = video;
    }

    public VistiedVideo(Long uid, String video) {
        this.uid = uid;
        this.video = video;
    }

    @Override
    public String toString() {
        return "VistiedVideo{" +
                "id=" + id +
                ", uid=" + uid +
                ", video='" + video + '\'' +
                '}';
    }
}
