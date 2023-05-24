package com.example.greenvillage.models;

public class Product {
    private int id;
    private String refProduit;
    private String shortLibel;
    private String longLibel;
    private double prixHt;
    private String slug;

    private Product(int id, String refProduit, String shortLibel, String longLibel, double prixHt, String slug){
        this.id = id;
        this.refProduit = refProduit;
        this.shortLibel = shortLibel;
        this.longLibel = longLibel;
        this.prixHt = prixHt;
        this.slug = slug;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(String refProduit) {
        this.refProduit = refProduit;
    }

    public String getShortLibel() {
        return shortLibel;
    }

    public void setShortLibel(String shortLibel) {
        this.shortLibel = shortLibel;
    }

    public String getLongLibel() {
        return longLibel;
    }

    public void setLongLibel(String longLibel) {
        this.longLibel = longLibel;
    }

    public double getPrixHt() {
        return prixHt;
    }

    public void setPrixHt(double prixHt) {
        this.prixHt = prixHt;
    }

    public String getSlug() { return slug; }

    public void setSlug(String slug) { this.slug = slug;}

}
