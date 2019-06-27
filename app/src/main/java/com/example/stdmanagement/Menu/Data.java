package com.example.stdmanagement.Menu;

public class Data {
    private String type;
    private String name;
    private String content;
    private int resId;

   public String getType(){return type;}
   public void setType(String type) {this.type = type;}


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}