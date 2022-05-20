package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * article对象 laf_article
 * 
 * @author yang
 * @date 2022-05-09
 */
public class LafArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 认领地点 */
    @Excel(name = "认领地点")
    private String claimPlace;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long flow;

    /** 点赞量 */
    @Excel(name = "点赞量")
    private Long star;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setClaimPlace(String claimPlace) 
    {
        this.claimPlace = claimPlace;
    }

    public String getClaimPlace() 
    {
        return claimPlace;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setFlow(Long flow) 
    {
        this.flow = flow;
    }

    public Long getFlow() 
    {
        return flow;
    }
    public void setStar(Long star) 
    {
        this.star = star;
    }

    public Long getStar() 
    {
        return star;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("claimPlace", getClaimPlace())
            .append("contact", getContact())
            .append("unit", getUnit())
            .append("flow", getFlow())
            .append("star", getStar())
            .toString();
    }
}
