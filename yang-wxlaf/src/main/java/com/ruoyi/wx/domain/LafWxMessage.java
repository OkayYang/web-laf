package com.ruoyi.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

public class LafWxMessage {
    private static final long serialVersionUID = 1L;

    /** 帖子id */

    private Long replyTieziId;

    /** 评论内容 */

    private String replyInfo;

    /** 评论图片 */

    private String replyImage;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date replyTime;

    /** 学生别名昵称 */

    private String replyName;

    /** 浏览量 */

    private String replyedTieziRead;

    /** 帖子标题 */
    private String replyedTiezi;

    /** 头像 */
    private String avatar;

    /** 评论内容 */
    private String replyedInfo;

    /** 发布者id */
    private Long relStuid;

    /** 评论者id */
    private Long parentStuid;

    /** 评论图片 */
    private String replyedImage;

    public void setReplyTieziId(Long replyTieziId)
    {
        this.replyTieziId = replyTieziId;
    }

    public Long getReplyTieziId()
    {
        return replyTieziId;
    }
    public void setReplyInfo(String replyInfo)
    {
        this.replyInfo = replyInfo;
    }

    public String getReplyInfo()
    {
        return replyInfo;
    }
    public void setReplyImage(String replyImage)
    {
        this.replyImage = replyImage;
    }

    public String getReplyImage()
    {
        return replyImage;
    }
    public void setReplyTime(Date replyTime)
    {
        this.replyTime = replyTime;
    }

    public Date getReplyTime()
    {
        return replyTime;
    }
    public void setReplyName(String replyName)
    {
        this.replyName = replyName;
    }

    public String getReplyName()
    {
        return replyName;
    }
    public void setReplyedTieziRead(String replyedTieziRead)
    {
        this.replyedTieziRead = replyedTieziRead;
    }

    public String getReplyedTieziRead()
    {
        return replyedTieziRead;
    }
    public void setReplyedTiezi(String replyedTiezi)
    {
        this.replyedTiezi = replyedTiezi;
    }

    public String getReplyedTiezi()
    {
        return replyedTiezi;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setReplyedInfo(String replyedInfo)
    {
        this.replyedInfo = replyedInfo;
    }

    public String getReplyedInfo()
    {
        return replyedInfo;
    }
    public void setRelStuid(Long relStuid)
    {
        this.relStuid = relStuid;
    }

    public Long getRelStuid()
    {
        return relStuid;
    }
    public void setParentStuid(Long parentStuid)
    {
        this.parentStuid = parentStuid;
    }

    public Long getParentStuid()
    {
        return parentStuid;
    }
    public void setReplyedImage(String replyedImage)
    {
        this.replyedImage = replyedImage;
    }

    public String getReplyedImage()
    {
        return replyedImage;
    }
}
