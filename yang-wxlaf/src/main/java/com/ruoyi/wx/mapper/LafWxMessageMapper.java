package com.ruoyi.wx.mapper;

import com.ruoyi.wx.domain.LafWxMessage;

import java.util.List;

/**
 * 我的消息Mapper接口
 *
 * @author yang
 * @date 2022-04-21
 */
public interface LafWxMessageMapper {
    /**
     * 查询我的消息列表
     *
     * @param id 身份标识
     * @return 我的消息集合
     */
    public List<LafWxMessage> selectLafWxMessageList(Long id);
}
