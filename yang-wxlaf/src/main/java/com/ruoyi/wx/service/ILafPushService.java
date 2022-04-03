package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafPush;

/**
 * 失物推送Service接口
 * 
 * @author yang
 * @date 2022-03-24
 */
public interface ILafPushService 
{
    /**
     * 查询失物推送
     * 
     * @param pushId 失物推送主键
     * @return 失物推送
     */
    public LafPush selectLafPushByPushId(Long pushId);

    /**
     * 查询失物推送列表
     * 
     * @param lafPush 失物推送
     * @return 失物推送集合
     */
    public List<LafPush> selectLafPushList(LafPush lafPush);

    /**
     * 新增失物推送
     * 
     * @param lafPush 失物推送
     * @return 结果
     */
    public int insertLafPush(LafPush lafPush);

    /**
     * 修改失物推送
     * 
     * @param lafPush 失物推送
     * @return 结果
     */
    public int updateLafPush(LafPush lafPush);

    /**
     * 批量删除失物推送
     * 
     * @param pushIds 需要删除的失物推送主键集合
     * @return 结果
     */
    public int deleteLafPushByPushIds(String pushIds);

    /**
     * 删除失物推送信息
     * 
     * @param pushId 失物推送主键
     * @return 结果
     */
    public int deleteLafPushByPushId(Long pushId);

    /**
     * 每次推送更新表
     * @param relId 推送的帖子id
     * @return
     */
    public int updateCount(Long pid ,String relId);
}
