package com.ruoyi.wx.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 小程序公告对象 laf_announce
 * 
 * @author yang
 * @date 2021-12-20
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

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 图片 */
    @Excel(name = "图片")
    private String image;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

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
    public void setCreatTime(Date creatTime) 
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime() 
    {
        return creatTime;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("annId", getAnnId())
            .append("annTitle", getAnnTitle())
            .append("annContent", getAnnContent())
            .append("createBy", getCreateBy())
            .append("creatTime", getCreatTime())
            .append("image", getImage())
            .append("status", getStatus())
            .toString();
    }
}
