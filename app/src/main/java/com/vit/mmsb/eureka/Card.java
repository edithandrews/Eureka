package com.vit.mmsb.eureka;

public class Card {
    private String title;
    private String description;
    private int priority;

    public Card() {
        //empty constructor needed
    }

    public Card(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}