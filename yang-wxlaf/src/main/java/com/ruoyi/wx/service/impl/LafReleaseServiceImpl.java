package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.util.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafReleaseMapper;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.service.ILafReleaseService;
import com.ruoyi.common.core.text.Convert;

/**
 * 帖子Service业务层处理
 * 
 * @author yang
 * @date 2021-10-08
 */
@Service
public class LafReleaseServiceImpl implements ILafReleaseService 
{
    @Autowired
    private LafReleaseMapper lafReleaseMapper;

    /**
     * 查询帖子
     * 
     * @param relId 帖子主键
     * @return 帖子
     */
    @Override
    public LafRelease selectLafReleaseByRelId(Long relId)
    {
        return lafReleaseMapper.selectLafReleaseByRelId(relId);
    }

    /**
     * 查询帖子列表
     * 
     * @param lafRelease 帖子
     * @return 帖子
     */
    @Override
    public List<LafRelease> selectLafReleaseList(LafRelease lafRelease)
    {
        return lafReleaseMapper.selectLafReleaseList(lafRelease);
    }

    /**
     * 新增帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    @Override
    public int insertLafRelease(LafRelease lafRelease)
    {
        lafRelease.setCreateTime(DateUtils.getNowDate());
        return lafReleaseMapper.insertLafRelease(lafRelease);
    }

    /**
     * 修改帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    @Override
    public int updateLafRelease(LafRelease lafRelease)
    {
        return lafReleaseMapper.updateLafRelease(lafRelease);
    }

    /**
     * 批量删除帖子
     * 
     * @param relIds 需要删除的帖子主键
     * @return 结果
     */
    @Override
    public int deleteLafReleaseByRelIds(String relIds)
    {
        return lafReleaseMapper.deleteLafReleaseByRelIds(Convert.toStrArray(relIds));
    }

    /**
     * 删除帖子信息
     * 
     * @param relId 帖子主键
     * @return 结果
     */
    @Override
    public int deleteLafReleaseByRelId(Long relId)
    {
        return lafReleaseMapper.deleteLafReleaseByRelId(relId);
    }

    /**
     * 统计信息量
     */
    public int informationCount(){
        return lafReleaseMapper.informationCount();
    }
    /**
     * 统计成功量
     */
    public int successCount(){
        return lafReleaseMapper.successCount();
    }

    /**
     * 扇形图数据
     */

    public List<Graph> fanChart(){
        return lafReleaseMapper.fanChart();
    }
}
