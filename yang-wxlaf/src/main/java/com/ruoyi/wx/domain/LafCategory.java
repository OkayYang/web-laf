package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 物品种类对象 laf_category
 * 
 * @author yang
 * @date 2021-10-08
 */
public class LafCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 物品ID */
    private Long cateId;

    /** 父ID */
    @Excel(name = "父ID")
    private Long paraentId;

    /** 名称 */
    @Excel(name = "名称")
    private String cateName;

    /** 状态 */
    @Excel(name = "状态")
    private String cateStutus;

    public void setCateId(Long cateId) 
    {
        this.cateId = cateId;
    }

    public Long getCateId() 
    {
        return cateId;
    }
    public void setParaentId(Long paraentId) 
    {
        this.paraentId = paraentId;
    }

    public Long getParaentId() 
    {
        return paraentId;
    }
    public void setCateName(String cateName) 
    {
        this.cateName = cateName;
    }

    public String getCateName() 
    {
        return cateName;
    }
    public void setCateStutus(String cateStutus) 
    {
        this.cateStutus = cateStutus;
    }

    public String getCateStutus() 
    {
        return cateStutus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cateId", getCateId())
            .append("paraentId", getParaentId())
            .append("cateName", getCateName())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("cateStutus", getCateStutus())
            .toString();
    }
}
