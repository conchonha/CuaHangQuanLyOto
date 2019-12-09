package com.example.quanlyoto_doan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Accessory {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("picturedesscription")
    @Expose
    private String picturedesscription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturedesscription() {
        return picturedesscription;
    }

    public void setPicturedesscription(String picturedesscription) {
        this.picturedesscription = picturedesscription;
    }

}