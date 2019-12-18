package com.example.quanlyoto_doan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("nameservices")
@Expose
private String nameservices;
@SerializedName("priceservices")
@Expose
private Integer priceservices;
@SerializedName("pictureicon")
@Expose
private String pictureicon;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getNameservices() {
return nameservices;
}

public void setNameservices(String nameservices) {
this.nameservices = nameservices;
}

public Integer getPriceservices() {
return priceservices;
}

public void setPriceservices(Integer priceservices) {
this.priceservices = priceservices;
}

public String getPictureicon() {
return pictureicon;
}

public void setPictureicon(String pictureicon) {
this.pictureicon = pictureicon;
}

}