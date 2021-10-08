package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafStudent;

/**
 * 学生Mapper接口
 * 
 * @author yang
 * @date 2021-10-08
 */
public interface LafStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param stuId 学生主键
     * @return 学生
     */
    public LafStudent selectLafStudentByStuId(Long stuId);

    /**
     * 查询学生列表
     * 
     * @param lafStudent 学生
     * @return 学生集合
     */
    public List<LafStudent> selectLafStudentList(LafStudent lafStudent);

    /**
     * 新增学生
     * 
     * @param lafStudent 学生
     * @return 结果
     */
    public int insertLafStudent(LafStudent lafStudent);

    /**
     * 修改学生
     * 
     * @param lafStudent 学生
     * @return 结果
     */
    public int updateLafStudent(LafStudent lafStudent);

    /**
     * 删除学生
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    public int deleteLafStudentByStuId(Long stuId);

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafStudentByStuIds(String[] stuIds);
}
