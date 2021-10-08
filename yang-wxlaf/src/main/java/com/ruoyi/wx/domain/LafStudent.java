package com.ruoyi.wx.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学生对象 laf_student
 * 
 * @author yang
 * @date 2021-10-08
 */
public class LafStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生ID */
    private Long stuId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String stuNick;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 性别 */
    @Excel(name = "性别")
    private String stuSex;

    /** 学号 */
    @Excel(name = "学号")
    private String stuXh;

    /** 密码 */
    @Excel(name = "密码")
    private String stuPassword;

    /** 学院 */
    @Excel(name = "学院")
    private String stuDepartment;

    /** 专业 */
    @Excel(name = "专业")
    private String stuMajor;

    /** 班级 */
    @Excel(name = "班级")
    private String stuClassname;

    /** QQ */
    @Excel(name = "QQ")
    private String stuQq;

    /** email */
    @Excel(name = "email")
    private String stuEmail;

    /** 头像 */
    private String stuImage;

    /** 状态 */
    @Excel(name = "状态")
    private String stuStatus;

    /** openId */
    @Excel(name = "openId")
    private String openid;

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
    public void setStuXh(String stuXh) 
    {
        this.stuXh = stuXh;
    }

    public String getStuXh() 
    {
        return stuXh;
    }
    public void setStuPassword(String stuPassword) 
    {
        this.stuPassword = stuPassword;
    }

    public String getStuPassword() 
    {
        return stuPassword;
    }
    public void setStuDepartment(String stuDepartment) 
    {
        this.stuDepartment = stuDepartment;
    }

    public String getStuDepartment() 
    {
        return stuDepartment;
    }
    public void setStuMajor(String stuMajor) 
    {
        this.stuMajor = stuMajor;
    }

    public String getStuMajor() 
    {
        return stuMajor;
    }
    public void setStuClassname(String stuClassname) 
    {
        this.stuClassname = stuClassname;
    }

    public String getStuClassname() 
    {
        return stuClassname;
    }
    public void setStuQq(String stuQq) 
    {
        this.stuQq = stuQq;
    }

    public String getStuQq() 
    {
        return stuQq;
    }
    public void setStuEmail(String stuEmail) 
    {
        this.stuEmail = stuEmail;
    }

    public String getStuEmail() 
    {
        return stuEmail;
    }
    public void setStuImage(String stuImage) 
    {
        this.stuImage = stuImage;
    }

    public String getStuImage() 
    {
        return stuImage;
    }
    public void setStuStatus(String stuStatus) 
    {
        this.stuStatus = stuStatus;
    }

    public String getStuStatus() 
    {
        return stuStatus;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("stuNick", getStuNick())
            .append("stuName", getStuName())
            .append("stuSex", getStuSex())
            .append("stuXh", getStuXh())
            .append("stuPassword", getStuPassword())
            .append("stuDepartment", getStuDepartment())
            .append("stuMajor", getStuMajor())
            .append("stuClassname", getStuClassname())
            .append("stuQq", getStuQq())
            .append("stuEmail", getStuEmail())
            .append("stuImage", getStuImage())
            .append("createTime", getCreateTime())
            .append("stuStatus", getStuStatus())
            .append("openid", getOpenid())
            .toString();
    }
}
