package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序公告对象 laf_announce
 * 
 * @author yang
 * @date 2022-03-25
 */
public class LafAnnounce extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告id */
    private Long annId;

    /** 标题类型 */
    @Excel(name = "标题类型")
    private String annTitle;

    /** 内容 */
    @Excel(name = "内容")
    private String annContent;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long flow;

    public void setAnnId(Long annId) 
    {
        this.annId = annId;
    }

    public Long getAnnId() 
    {
        return annId;
    }
    public void setAnnTitle(String annTitle) 
    {
        this.annTitle = annTitle;
    }

    public String getAnnTitle() 
    {
        return annTitle;
    }
    public void setAnnContent(String annContent) 
    {
        this.annContent = annContent;
    }

    public String getAnnContent() 
    {
        return annContent;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setFlow(Long flow) 
    {
        this.flow = flow;
    }

    public Long getFlow() 
    {
        return flow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("annId", getAnnId())
            .append("annTitle", getAnnTitle())
            .append("annContent", getAnnContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("image", getImage())
            .append("status", getStatus())
            .append("flow", getFlow())
            .toString();
    }
}
