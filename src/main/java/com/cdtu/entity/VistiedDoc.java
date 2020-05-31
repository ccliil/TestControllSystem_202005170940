package com.cdtu.entity;

public class VistiedDoc {
    private Long did;
    private Long uid;
    private String doc;

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public VistiedDoc() {
    }

    public VistiedDoc(Long uid, String doc) {
        this.uid = uid;
        this.doc = doc;
    }

    public VistiedDoc(Long did, Long uid, String doc) {
        this.did = did;
        this.uid = uid;
        this.doc = doc;
    }

    @Override
    public String toString() {
        return "VistiedDoc{" +
                "did=" + did +
                ", uid=" + uid +
                ", doc='" + doc + '\'' +
                '}';
    }
}
