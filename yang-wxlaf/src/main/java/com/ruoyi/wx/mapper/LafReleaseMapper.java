package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafRelease;
import com.ruoyi.wx.util.Graph;

/**
 * 帖子Mapper接口
 * 
 * @author yang
 * @date 2021-10-08
 */
public interface LafReleaseMapper 
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
     * 删除帖子
     * 
     * @param relId 帖子主键
     * @return 结果
     */
    public int deleteLafReleaseByRelId(Long relId);

    /**
     * 批量删除帖子
     * 
     * @param relIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafReleaseByRelIds(String[] relIds);

    /**
     * 统计信息量
     */
    public int informationCount();
    /**
     * 统计成功量
     */
    public int successCount();
    /**
     * 扇形图数据
     */

    public List<Graph> fanChart();
}
