package com.example.stdmanagement.MenuComment;

public class CommentItems {

    private String stdID;
    private String comment;

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
