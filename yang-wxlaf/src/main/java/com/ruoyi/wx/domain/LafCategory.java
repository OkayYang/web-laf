package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 物品种类对象 laf_category
 *
 * @author yang
 * @date 2022-02-11
 */
public class LafCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 物品ID */
    private Long cateId;

    /** 名称 */
    @Excel(name = "名称")
    private String cateName;

    /** 状态(1使用中，2已删除） */
    @Excel(name = "状态(1使用中，2已删除）")
    private String cateStatus;

    public void setCateId(Long cateId)
    {
        this.cateId = cateId;
    }

    public Long getCateId()
    {
        return cateId;
    }
    public void setCateName(String cateName)
    {
        this.cateName = cateName;
    }

    public String getCateName()
    {
        return cateName;
    }
    public void setCateStatus(String cateStatus)
    {
        this.cateStatus = cateStatus;
    }

    public String getCateStatus()
    {
        return cateStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("cateId", getCateId())
                .append("parentId", getParentId())
                .append("cateName", getCateName())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("cateStatus", getCateStatus())
                .toString();
    }
}