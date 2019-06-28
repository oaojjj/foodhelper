package com.example.stdmanagement.MenuComment;

public class CommentItems {

    private String stdID;
    private String comment;
    private String date;

    public CommentItems(String stdID, String comment, String date) {
        this.stdID = stdID;
        this.comment = comment;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CommentItems(String stdID, String comment) {
        this.stdID = stdID;
        this.comment = comment;
    }


    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
