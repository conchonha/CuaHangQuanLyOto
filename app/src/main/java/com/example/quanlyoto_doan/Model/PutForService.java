package com.example.quanlyoto_doan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PutForService {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("putdate")
@Expose
private String putdate;
@SerializedName("time")
@Expose
private String time;
@SerializedName("speedometer")
@Expose
private Integer speedometer;
@SerializedName("note")
@Expose
private String note;
@SerializedName("type")
@Expose
private String type;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("address")
@Expose
private String address;
@SerializedName("idacount")
@Expose
private Integer idacount;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getPutdate() {
return putdate;
}

public void setPutdate(String putdate) {
this.putdate = putdate;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public Integer getSpeedometer() {
return speedometer;
}

public void setSpeedometer(Integer speedometer) {
this.speedometer = speedometer;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Integer getPrice() {
return price;
}

public void setPrice(Integer price) {
this.price = price;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public Integer getIdacount() {
return idacount;
}

public void setIdacount(Integer idacount) {
this.idacount = idacount;
}

}