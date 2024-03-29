package com.ruoyi.wx.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 帖子对象 laf_release
 *
 * @author yang
 * @date 2022-02-27
 */
public class LafRelease extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子ID */
    private Long relId;

    /** 标题 */
    @Excel(name = "标题")
    private String relTitle;

    /** 描述 */
    @Excel(name = "描述")
    private String relDesc;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String relContact;

    /** 物品种类 */
    @Excel(name = "物品种类")
    private Long relCateId;

    /** 认领时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "认领时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date relTime;

    /** 校区 */
    @Excel(name = "校区")
    private String relCampus;

    /** 地点 */
    @Excel(name = "地点")
    private String createPlace;

    /** 发布者ID */
    @Excel(name = "发布者ID")
    private Long createId;

    /** 图片 */
    @Excel(name = "图片")
    private String relImage;

    /** 帖子类别(1代表失物，2代表寻物) */
    @Excel(name = "帖子类别(1代表失物，2代表寻物)")
    private String relStatus;

    /** 赞 */
    @Excel(name = "赞")
    private Long relStar;

    /** 状态 */
    @Excel(name = "状态")
    private String relFlag;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long relFlow;

    /** 删除 */
    @Excel(name = "删除")
    private String relDel;

    /** 认领者id */
    @Excel(name = "认领者id")
    private Long relClaimId;

    public void setRelId(Long relId)
    {
        this.relId = relId;
    }

    public Long getRelId()
    {
        return relId;
    }
    public void setRelTitle(String relTitle)
    {
        this.relTitle = relTitle;
    }

    public String getRelTitle()
    {
        return relTitle;
    }
    public void setRelDesc(String relDesc)
    {
        this.relDesc = relDesc;
    }

    public String getRelDesc()
    {
        return relDesc;
    }
    public void setRelContact(String relContact)
    {
        this.relContact = relContact;
    }

    public String getRelContact()
    {
        return relContact;
    }
    public void setRelCateId(Long relCateId)
    {
        this.relCateId = relCateId;
    }

    public Long getRelCateId()
    {
        return relCateId;
    }
    public void setRelTime(Date relTime)
    {
        this.relTime = relTime;
    }

    public Date getRelTime()
    {
        return relTime;
    }
    public void setRelCampus(String relCampus)
    {
        this.relCampus = relCampus;
    }

    public String getRelCampus()
    {
        return relCampus;
    }
    public void setCreatePlace(String createPlace)
    {
        this.createPlace = createPlace;
    }

    public String getCreatePlace()
    {
        return createPlace;
    }
    public void setCreateId(Long createId)
    {
        this.createId = createId;
    }

    public Long getCreateId()
    {
        return createId;
    }
    public void setRelImage(String relImage)
    {
        this.relImage = relImage;
    }

    public String getRelImage()
    {
        return relImage;
    }
    public void setRelStatus(String relStatus)
    {
        this.relStatus = relStatus;
    }

    public String getRelStatus()
    {
        return relStatus;
    }
    public void setRelStar(Long relStar)
    {
        this.relStar = relStar;
    }

    public Long getRelStar()
    {
        return relStar;
    }
    public void setRelFlag(String relFlag)
    {
        this.relFlag = relFlag;
    }

    public String getRelFlag()
    {
        return relFlag;
    }
    public void setRelFlow(Long relFlow)
    {
        this.relFlow = relFlow;
    }

    public Long getRelFlow()
    {
        return relFlow;
    }
    public void setRelDel(String relDel)
    {
        this.relDel = relDel;
    }

    public String getRelDel()
    {
        return relDel;
    }
    public void setRelClaimId(Long relClaimId)
    {
        this.relClaimId = relClaimId;
    }

    public Long getRelClaimId()
    {
        return relClaimId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("relId", getRelId())
                .append("relTitle", getRelTitle())
                .append("relDesc", getRelDesc())
                .append("relContact", getRelContact())
                .append("relCateId", getRelCateId())
                .append("relTime", getRelTime())
                .append("relCampus", getRelCampus())
                .append("createPlace", getCreatePlace())
                .append("createTime", getCreateTime())
                .append("createId", getCreateId())
                .append("relImage", getRelImage())
                .append("relStatus", getRelStatus())
                .append("relStar", getRelStar())
                .append("relFlag", getRelFlag())
                .append("relFlow", getRelFlow())
                .append("relDel", getRelDel())
                .append("relClaimId", getRelClaimId())
                .toString();
    }
}