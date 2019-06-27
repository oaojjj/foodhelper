package com.example.stdmanagement.Retrofit;

import java.util.ArrayList;
import java.util.Map;

public class RetrofitData {
    String id;
    String cafe_type;
    String kor_name;
    String eng_name;
    String type;
    String price;
    String info;
    String avg_rating;

    static ArrayList<Map<String,String>> arrayList;

    static public ArrayList<Map<String, String>> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Map<String, String>> arrayList) {
        this.arrayList = arrayList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCafe_type() {
        return cafe_type;
    }

    public void setCafe_type(String cafe_type) {
        this.cafe_type = cafe_type;
    }

    public String getKor_name() {
        return kor_name;
    }

    public void setKor_name(String kor_name) {
        this.kor_name = kor_name;
    }

    public String getEng_name() {
        return eng_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(String avg_rating) {
        this.avg_rating = avg_rating;
    }
}
