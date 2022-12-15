package com.learning.entity;

import groovy.transform.builder.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Post {
    private Long id;
    private String title;
    private String anons;
    private String ful_text;
    private int views;

    private boolean enabled;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFul_text() {
        return ful_text;
    }

    public void setFul_text(String ful_text) {
        this.ful_text = ful_text;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
