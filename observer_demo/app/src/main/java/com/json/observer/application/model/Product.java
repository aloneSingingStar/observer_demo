package com.json.observer.application.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/20.
 */
public class Product implements Serializable {
    private static final Long serialVersionUID=1L;

    public Product() {
    }

    public Product(int id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    private int id;
    private String name;
    private String description;
    private String imgUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Emotion{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
