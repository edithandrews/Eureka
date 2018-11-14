package com.vit.mmsb.eureka;

public class Comment {
    private String com;
    private int likes;

    public Comment() {
        //empty constructor needed
    }

    public Comment(String com, int likes) {
        this.com = com;
        this.likes = likes;
    }

    public String getComment() {
        return com;
    }
    public int getLikes() { return likes; }
}