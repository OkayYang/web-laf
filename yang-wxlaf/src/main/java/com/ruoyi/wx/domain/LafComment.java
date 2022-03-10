package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 帖子留言对象 laf_comment
 * 
 * @author yang
 * @date 2022-03-04
 */
public class LafComment extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long comId;

    /** 文章ID */
    @Excel(name = "文章ID")
    private Long comRelId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long comStuId;

    /** 内容 */
    @Excel(name = "内容")
    private String comContent;

    /** 赞 */
    @Excel(name = "赞")
    private Long comStar;

    /** 状态 */
    @Excel(name = "状态")
    private String comStatus;

    /** 图片 */
    @Excel(name = "图片")
    private String comImage;

    public void setComId(Long comId) 
    {
        this.comId = comId;
    }

    public Long getComId() 
    {
        return comId;
    }
    public void setComRelId(Long comRelId) 
    {
        this.comRelId = comRelId;
    }

    public Long getComRelId() 
    {
        return comRelId;
    }
    public void setComStuId(Long comStuId) 
    {
        this.comStuId = comStuId;
    }

    public Long getComStuId() 
    {
        return comStuId;
    }
    public void setComContent(String comContent) 
    {
        this.comContent = comContent;
    }

    public String getComContent() 
    {
        return comContent;
    }
    public void setComStar(Long comStar) 
    {
        this.comStar = comStar;
    }

    public Long getComStar() 
    {
        return comStar;
    }
    public void setComStatus(String comStatus) 
    {
        this.comStatus = comStatus;
    }

    public String getComStatus() 
    {
        return comStatus;
    }
    public void setComImage(String comImage) 
    {
        this.comImage = comImage;
    }

    public String getComImage() 
    {
        return comImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("comId", getComId())
            .append("parentId", getParentId())
            .append("comRelId", getComRelId())
            .append("comStuId", getComStuId())
            .append("comContent", getComContent())
            .append("createTime", getCreateTime())
            .append("comStar", getComStar())
            .append("comStatus", getComStatus())
            .append("comImage", getComImage())
            .toString();
    }
}
