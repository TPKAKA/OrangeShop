/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dell E7450
 */
public class Comments {
    private int commentId;
    private int product_comment;
    private int userid;
    private String details;
     private int vote;
    private int level;
    private int replyId;
    private int origin_comment_id;
    private String cmt_image;

    public String getCmt_image() {
        return cmt_image;
    }

    public void setCmt_image(String cmt_image) {
        this.cmt_image = cmt_image;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProduct_comment() {
        return product_comment;
    }

    public void setProduct_comment(int product_comment) {
        this.product_comment = product_comment;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getOrigin_comment_id() {
        return origin_comment_id;
    }

    public void setOrigin_comment_id(int origin_comment_id) {
        this.origin_comment_id = origin_comment_id;
    }

    public Comments() {
    }

    public Comments(int commentId, int product_comment, int userid, String details, int vote, int level, int replyId, int origin_comment_id, String cmt_image) {
        this.commentId = commentId;
        this.product_comment = product_comment;
        this.userid = userid;
        this.details = details;
        this.vote = vote;
        this.level = level;
        this.replyId = replyId;
        this.origin_comment_id = origin_comment_id;
        this.cmt_image = cmt_image;
    }

    

}