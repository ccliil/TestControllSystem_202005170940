package com.cdtu.entity;

public class Major {
    private Long mid;
    private String name;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "major{" +
                "mid=" + mid +
                ", name='" + name + '\'' +
                '}';
    }
}
