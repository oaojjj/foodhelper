package com.example.stdmanagement;

public class Data {
    private String type;
    private String name;
    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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