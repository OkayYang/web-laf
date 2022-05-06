package com.ruoyi.wx.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LafClaimStudent {
    /** 学生ID */
    private Long stuId;

    /** 昵称 */
    private String stuNick;

    /** 姓名 */
    private String stuName;

    /** 性别 */
    private String stuSex;

    /** 学号 */
    private String stuXh;
    /** 学院 */
    private String stuDepartment;

    /** 专业 */
    private String stuMajor;

    /** 班级 */
    private String stuClassname;

    /** QQ */
    private String stuQq;

    /** email */
    private String stuEmail;

    /** 头像 */
    private String profile;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getStuNick() {
        return stuNick;
    }

    public void setStuNick(String stuNick) {
        this.stuNick = stuNick;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuXh() {
        return stuXh;
    }

    public void setStuXh(String stuXh) {
        this.stuXh = stuXh;
    }

    public String getStuDepartment() {
        return stuDepartment;
    }

    public void setStuDepartment(String stuDepartment) {
        this.stuDepartment = stuDepartment;
    }

    public String getStuMajor() {
        return stuMajor;
    }

    public void setStuMajor(String stuMajor) {
        this.stuMajor = stuMajor;
    }

    public String getStuClassname() {
        return stuClassname;
    }

    public void setStuClassname(String stuClassname) {
        this.stuClassname = stuClassname;
    }

    public String getStuQq() {
        return stuQq;
    }

    public void setStuQq(String stuQq) {
        this.stuQq = stuQq;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


}
