package com.example.greenvillage.models;

public class SubCategory {

    private int id;
    private String nomination;
    private String slug;

    public SubCategory(int id, String nomination, String slug) {
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


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

}
