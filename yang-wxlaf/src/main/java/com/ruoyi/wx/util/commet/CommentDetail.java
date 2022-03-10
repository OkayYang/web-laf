package com.ruoyi.wx.util.commet;

import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.wx.util.User;

public class CommentDetail {
    private LafComment comment;
    private User comm_user;
    private User reply_user;

    public CommentDetail() {
    }

    public CommentDetail(LafComment comment, User comm_user, User reply_user) {
        this.comment = comment;
        this.comm_user = comm_user;
        this.reply_user = reply_user;
    }


    public LafComment getComment() {
        return comment;
    }

    public void setComment(LafComment comment) {
        this.comment = comment;
    }

    public User getComm_user() {
        return comm_user;
    }

    public void setComm_user(User comm_user) {
        this.comm_user = comm_user;
    }

    public User getReply_user() {
        return reply_user;
    }

    public void setReply_user(User reply_user) {
        this.reply_user = reply_user;
    }
}
