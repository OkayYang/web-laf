package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.util.echart.Graph;

/**
 * 帖子Service接口
 * 
 * @author yang
 * @date 2021-10-08
 */
public interface ILafReleaseService 
{
    /**
     * 查询帖子
     * 
     * @param relId 帖子主键
     * @return 帖子
     */
    public LafRelease selectLafReleaseByRelId(Long relId);

    /**
     * 查询帖子列表
     * 
     * @param lafRelease 帖子
     * @return 帖子集合
     */
    public List<LafRelease> selectLafReleaseList(LafRelease lafRelease);

    /**
     * 新增帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    public int insertLafRelease(LafRelease lafRelease);

    /**
     * 修改帖子
     * 
     * @param lafRelease 帖子
     * @return 结果
     */
    public int updateLafRelease(LafRelease lafRelease);

    /**
     * 批量删除帖子
     * 
     * @param relIds 需要删除的帖子主键集合
     * @return 结果
     */
    public int deleteLafReleaseByRelIds(String relIds);

    /**
     * 删除帖子信息
     * 
     * @param relId 帖子主键
     * @return 结果
     */
    public int deleteLafReleaseByRelId(Long relId);
    /**
     * 统计信息量
     */
    public int informationCount();
    /**
     * 统计成功量
     */
    public int successCount();
    /**
     * 扇形图
     */
    public List<Graph> fanChart();
}
