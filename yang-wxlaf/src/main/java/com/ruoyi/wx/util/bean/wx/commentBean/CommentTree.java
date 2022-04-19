package com.ruoyi.wx.util.bean.wx.commentBean;

import java.util.List;

public class CommentTree {
    private CommentDetail mainComments;
    private List<CommentDetail> replyComments;

    public CommentDetail getMainComments() {
        return mainComments;
    }

    public void setMainComments(CommentDetail mainComments) {
        this.mainComments = mainComments;
    }

    public List<CommentDetail> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<CommentDetail> replyComments) {
        this.replyComments = replyComments;
    }

    public CommentTree(CommentDetail mainComments, List<CommentDetail> replyComments) {
        this.mainComments = mainComments;
        this.replyComments = replyComments;
    }
}
