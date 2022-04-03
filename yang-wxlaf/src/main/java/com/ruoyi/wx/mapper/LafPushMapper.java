package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafPush;
import org.apache.ibatis.annotations.Param;

/**
 * 失物推送Mapper接口
 * 
 * @author yang
 * @date 2022-03-24
 */
public interface LafPushMapper 
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
     * 删除失物推送
     * 
     * @param pushId 失物推送主键
     * @return 结果
     */
    public int deleteLafPushByPushId(Long pushId);

    /**
     * 批量删除失物推送
     * 
     * @param pushIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafPushByPushIds(String[] pushIds);

    /**
     * 每次推送更新表
     * @param relId 推送的帖子id
     * @return
     */
    public int updateCount(@Param("pushId")Long pushId ,@Param("relId") String relId);
}
