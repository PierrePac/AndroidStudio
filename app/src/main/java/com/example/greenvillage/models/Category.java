package com.example.greenvillage.models;

public class Category {
    private int id;
    private String nomination;
    private String image;
    private String slug;

    public Category(int id, String nomination, String image, String slug) {
        this.id = id;
        this.nomination = nomination;
        this.image = image;
        this.slug = slug;
    }

    public Category(int id, String nomination, String slug) {
        this.id = id;
        this.nomination = nomination;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
