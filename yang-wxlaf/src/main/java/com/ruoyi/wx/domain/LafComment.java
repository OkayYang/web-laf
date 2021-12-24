package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 帖子留言对象 laf_comment
 * 
 * @author yang
 * @date 2021-12-22
 */
public class LafComment extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long comId;

    /** 父ID */
    @Excel(name = "父ID")
    private Long paraentId;

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

    public void setComId(Long comId) 
    {
        this.comId = comId;
    }

    public Long getComId() 
    {
        return comId;
    }
    public void setParaentId(Long paraentId) 
    {
        this.paraentId = paraentId;
    }

    public Long getParaentId() 
    {
        return paraentId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("comId", getComId())
            .append("paraentId", getParaentId())
            .append("comRelId", getComRelId())
            .append("comStuId", getComStuId())
            .append("comContent", getComContent())
            .append("createTime", getCreateTime())
            .append("comStar", getComStar())
            .append("comStatus", getComStatus())
            .toString();
    }
}
