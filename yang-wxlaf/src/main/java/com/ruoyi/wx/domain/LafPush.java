package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 失物推送对象 laf_push
 *
 * @author yang
 * @date 2022-03-24
 */
public class LafPush extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long pushId;

    /** 标题 */
    @Excel(name = "标题")
    private String pushTitle;

    /** 线索 */
    @Excel(name = "线索")
    private String pushClue;

    /** 帖子ID */
    @Excel(name = "帖子ID")
    private String relId;

    /** 种类ID */
    @Excel(name = "种类ID")
    private Long cateId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long stuId;

    /** 推送次数 */
    @Excel(name = "推送次数")
    private String pushCount;

    /** 相似度 */
    @Excel(name = "相似度")
    private Long pushSimilar;

    /** 状态 */
    @Excel(name = "状态")
    private String pushStatus;

    /** 删除 */
    @Excel(name = "删除")
    private String pushDel;

    public void setPushId(Long pushId)
    {
        this.pushId = pushId;
    }

    public Long getPushId()
    {
        return pushId;
    }
    public void setPushTitle(String pushTitle)
    {
        this.pushTitle = pushTitle;
    }

    public String getPushTitle()
    {
        return pushTitle;
    }
    public void setPushClue(String pushClue)
    {
        this.pushClue = pushClue;
    }

    public String getPushClue()
    {
        return pushClue;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public void setCateId(Long cateId)
    {
        this.cateId = cateId;
    }

    public Long getCateId()
    {
        return cateId;
    }
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setPushCount(String pushCount)
    {
        this.pushCount = pushCount;
    }

    public String getPushCount()
    {
        return pushCount;
    }
    public void setPushSimilar(Long pushSimilar)
    {
        this.pushSimilar = pushSimilar;
    }

    public Long getPushSimilar()
    {
        return pushSimilar;
    }
    public void setPushStatus(String pushStatus)
    {
        this.pushStatus = pushStatus;
    }

    public String getPushStatus()
    {
        return pushStatus;
    }
    public void setPushDel(String pushDel)
    {
        this.pushDel = pushDel;
    }

    public String getPushDel()
    {
        return pushDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("pushId", getPushId())
                .append("pushTitle", getPushTitle())
                .append("pushClue", getPushClue())
                .append("relId", getRelId())
                .append("cateId", getCateId())
                .append("stuId", getStuId())
                .append("createTime", getCreateTime())
                .append("pushCount", getPushCount())
                .append("pushSimilar", getPushSimilar())
                .append("pushStatus", getPushStatus())
                .append("pushDel", getPushDel())
                .toString();
    }
}