package com.example.greenvillage.models;

public class Image {

    private int id;
    private String src;
    private String majorPicture;
    private String refProduit;

    public Image(int id, String src, String majorPicture, String refProduit) {
        this.id = id;
        this.src = src;
        this.majorPicture = majorPicture;
        this.refProduit = refProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMajorPicture() {
        return majorPicture;
    }

    public void setMajorPicture(String majorPicture) {
        this.majorPicture = majorPicture;
    }

    public String getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(String refProduit) {
        this.refProduit = refProduit;
    }
}
