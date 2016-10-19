package com.scarb.model;

public class Comment {

    private Integer commentId;

    private Integer commentParentid;

    private Integer commentAuthorid;

    private String commentContent;

    private String commentTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentParentid() {
        return commentParentid;
    }

    public void setCommentParentid(Integer commentParentid) {
        this.commentParentid = commentParentid;
    }

    public Integer getCommentAuthorid() {
        return commentAuthorid;
    }

    public void setCommentAuthorid(Integer commentAuthorid) {
        this.commentAuthorid = commentAuthorid;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime == null ? null : commentTime.trim();
    }
    // NEW
    private String commentAuthorName;
    public String getCommentAuthorName(){
        return commentAuthorName;
    }
    public void setCommentAuthorName(String commentAuthorName){
        this.commentAuthorName = commentAuthorName == null ? null : commentAuthorName.trim();
    }
}