package com.example.quanlyoto_doan.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerhicleInfomation {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("producer")
@Expose
private String producer;
@SerializedName("type")
@Expose
private String type;
@SerializedName("capacity")
@Expose
private Integer capacity;
@SerializedName("contermet")
@Expose
private Integer contermet;
@SerializedName("idacount")
@Expose
private Integer idacount;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getProducer() {
return producer;
}

public void setProducer(String producer) {
this.producer = producer;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public Integer getCapacity() {
return capacity;
}

public void setCapacity(Integer capacity) {
this.capacity = capacity;
}

public Integer getContermet() {
return contermet;
}

public void setContermet(Integer contermet) {
this.contermet = contermet;
}

public Integer getIdacount() {
return idacount;
}

public void setIdacount(Integer idacount) {
this.idacount = idacount;
}

}