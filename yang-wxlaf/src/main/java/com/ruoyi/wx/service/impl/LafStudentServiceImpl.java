package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.domain.LafClaimStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafStudentMapper;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.service.ILafStudentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学生Service业务层处理
 * 
 * @author yang
 * @date 2021-10-08
 */
@Service
public class LafStudentServiceImpl implements ILafStudentService 
{
    @Autowired
    private LafStudentMapper lafStudentMapper;

    /**
     * 查询学生
     * 
     * @param stuId 学生主键
     * @return 学生
     */
    @Override
    public LafStudent selectLafStudentByStuId(Long stuId)
    {
        return lafStudentMapper.selectLafStudentByStuId(stuId);
    }

    /**
     * 查询学生列表
     * 
     * @param lafStudent 学生
     * @return 学生
     */
    @Override
    public List<LafStudent> selectLafStudentList(LafStudent lafStudent)
    {
        return lafStudentMapper.selectLafStudentList(lafStudent);
    }

    /**
     * 新增学生
     * 
     * @param lafStudent 学生
     * @return 结果
     */
    @Override
    public int insertLafStudent(LafStudent lafStudent)
    {
        lafStudent.setCreateTime(DateUtils.getNowDate());
        return lafStudentMapper.insertLafStudent(lafStudent);
    }

    /**
     * 修改学生
     * 
     * @param lafStudent 学生
     * @return 结果
     */
    @Override
    public int updateLafStudent(LafStudent lafStudent)
    {
        return lafStudentMapper.updateLafStudent(lafStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param stuIds 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteLafStudentByStuIds(String stuIds)
    {
        return lafStudentMapper.deleteLafStudentByStuIds(Convert.toStrArray(stuIds));
    }

    /**
     * 删除学生信息
     * 
     * @param stuId 学生主键
     * @return 结果
     */
    @Override
    public int deleteLafStudentByStuId(Long stuId)
    {
        return lafStudentMapper.deleteLafStudentByStuId(stuId);
    }
    @Override
    public int countNumbers(){
        return lafStudentMapper.countNumbers();
    }
    /**
     * 查询认领用户
     */
    @Override
    public LafClaimStudent selectLafClaimStudentList(Long uid) {
        return lafStudentMapper.selectLafClaimStudentList(uid);
    }
}
