package com.ruoyi.wx.domain;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 laf_wx_release
 *
 * @author yang
 * @date 2022-02-27
 */
public class LafWxRelease extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 帖子id */
    private Long relId;

    /** 帖子标题 */
    private String relTitle;

    /** 帖子描述 */
    private String relDesc;

    /** 帖子联系方式 */
    private String relContact;

    /** 所属物品种类id */
    private String relCateId;

    /** 物品丢失发现时间 */
    private Date relTime;

    /** 物品丢失寻找校区 */
    private String relCampus;

    /** 物品丢失发现地点 */
    private String createPlace;

    /** 帖子图片 */
    private String relImage;

    /** 获得赞 */
    private Integer relStar;

    /** 是否成功(1寻找中，2成功) */
    private String relFlag;

    /** 浏览量 */
    private String relFlow;

    /** 是否删除(1未删除,2删除 */
    private String relDel;

    /** 学生唯一表示id */
    private Long stuId;

    /** 学生别名昵称 */
    private String stuNick;

    /** 学生姓名 */
    private String stuName;

    /** 性别（1男，2女，3外星人） */
    private String stuSex;

    /** 头像 */
    private String stuAvatar;

    /** 帖子类别(1代表失物，2代表寻物) */
    private String relStatus;

    /** 认领者id */
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
    public void setRelCateId(String relCateId)
    {
        this.relCateId = relCateId;
    }

    public String getRelCateId()
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
    public void setRelImage(String relImage)
    {
        this.relImage = relImage;
    }

    public String getRelImage()
    {
        return relImage;
    }
    public void setRelStar(Integer relStar)
    {
        this.relStar = relStar;
    }

    public Integer getRelStar()
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
    public void setRelFlow(String relFlow)
    {
        this.relFlow = relFlow;
    }

    public String getRelFlow()
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
    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId()
    {
        return stuId;
    }
    public void setStuNick(String stuNick)
    {
        this.stuNick = stuNick;
    }

    public String getStuNick()
    {
        return stuNick;
    }
    public void setStuName(String stuName)
    {
        this.stuName = stuName;
    }

    public String getStuName()
    {
        return stuName;
    }
    public void setStuSex(String stuSex)
    {
        this.stuSex = stuSex;
    }

    public String getStuSex()
    {
        return stuSex;
    }
    public void setStuAvatar(String stuAvatar)
    {
        this.stuAvatar = stuAvatar;
    }

    public String getStuAvatar()
    {
        return stuAvatar;
    }
    public void setRelStatus(String relStatus)
    {
        this.relStatus = relStatus;
    }

    public String getRelStatus()
    {
        return relStatus;
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
                .append("relImage", getRelImage())
                .append("relStar", getRelStar())
                .append("relFlag", getRelFlag())
                .append("relFlow", getRelFlow())
                .append("relDel", getRelDel())
                .append("stuId", getStuId())
                .append("stuNick", getStuNick())
                .append("stuName", getStuName())
                .append("stuSex", getStuSex())
                .append("stuAvatar", getStuAvatar())
                .append("relStatus", getRelStatus())
                .append("relClaimId", getRelClaimId())
                .toString();
    }
}