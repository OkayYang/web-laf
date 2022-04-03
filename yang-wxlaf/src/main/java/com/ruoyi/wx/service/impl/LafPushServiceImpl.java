package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafPushMapper;
import com.ruoyi.wx.domain.LafPush;
import com.ruoyi.wx.service.ILafPushService;
import com.ruoyi.common.core.text.Convert;

/**
 * 失物推送Service业务层处理
 * 
 * @author yang
 * @date 2022-03-24
 */
@Service
public class LafPushServiceImpl implements ILafPushService 
{
    @Autowired
    private LafPushMapper lafPushMapper;

    /**
     * 查询失物推送
     * 
     * @param pushId 失物推送主键
     * @return 失物推送
     */
    @Override
    public LafPush selectLafPushByPushId(Long pushId)
    {
        return lafPushMapper.selectLafPushByPushId(pushId);
    }

    /**
     * 查询失物推送列表
     * 
     * @param lafPush 失物推送
     * @return 失物推送
     */
    @Override
    public List<LafPush> selectLafPushList(LafPush lafPush)
    {
        return lafPushMapper.selectLafPushList(lafPush);
    }

    /**
     * 新增失物推送
     * 
     * @param lafPush 失物推送
     * @return 结果
     */
    @Override
    public int insertLafPush(LafPush lafPush)
    {
        lafPush.setCreateTime(DateUtils.getNowDate());
        return lafPushMapper.insertLafPush(lafPush);
    }

    /**
     * 修改失物推送
     * 
     * @param lafPush 失物推送
     * @return 结果
     */
    @Override
    public int updateLafPush(LafPush lafPush)
    {
        return lafPushMapper.updateLafPush(lafPush);
    }

    /**
     * 批量删除失物推送
     * 
     * @param pushIds 需要删除的失物推送主键
     * @return 结果
     */
    @Override
    public int deleteLafPushByPushIds(String pushIds)
    {
        return lafPushMapper.deleteLafPushByPushIds(Convert.toStrArray(pushIds));
    }

    /**
     * 删除失物推送信息
     * 
     * @param pushId 失物推送主键
     * @return 结果
     */
    @Override
    public int deleteLafPushByPushId(Long pushId)
    {
        return lafPushMapper.deleteLafPushByPushId(pushId);
    }

    /**
     * 每次推送更新
     * @param relId 推送的帖子id
     * @return
     */
    @Override
    public int updateCount(Long pid ,String relId) {
        return lafPushMapper.updateCount(pid,relId);
    }

}
