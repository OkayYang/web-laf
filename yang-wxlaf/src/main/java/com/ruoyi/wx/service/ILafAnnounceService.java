package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafAnnounce;

/**
 * 小程序公告Service接口
 * 
 * @author yang
 * @date 2022-03-25
 */
public interface ILafAnnounceService 
{
    /**
     * 查询小程序公告
     * 
     * @param annId 小程序公告主键
     * @return 小程序公告
     */
    public LafAnnounce selectLafAnnounceByAnnId(Long annId);

    /**
     * 查询小程序公告列表
     * 
     * @param lafAnnounce 小程序公告
     * @return 小程序公告集合
     */
    public List<LafAnnounce> selectLafAnnounceList(LafAnnounce lafAnnounce);

    /**
     * 新增小程序公告
     * 
     * @param lafAnnounce 小程序公告
     * @return 结果
     */
    public int insertLafAnnounce(LafAnnounce lafAnnounce);

    /**
     * 修改小程序公告
     * 
     * @param lafAnnounce 小程序公告
     * @return 结果
     */
    public int updateLafAnnounce(LafAnnounce lafAnnounce);

    /**
     * 批量删除小程序公告
     * 
     * @param annIds 需要删除的小程序公告主键集合
     * @return 结果
     */
    public int deleteLafAnnounceByAnnIds(String annIds);

    /**
     * 删除小程序公告信息
     * 
     * @param annId 小程序公告主键
     * @return 结果
     */
    public int deleteLafAnnounceByAnnId(Long annId);
    /**
     * 更新浏览量
     * @return
     */
    public int updateBrowse(Long annId);
}
