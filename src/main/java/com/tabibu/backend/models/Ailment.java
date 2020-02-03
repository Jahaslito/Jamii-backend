package com.tabibu.backend.models;

public class Ailment {
    private String name;
    private String comment;

    public Ailment() {}

    public Ailment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
