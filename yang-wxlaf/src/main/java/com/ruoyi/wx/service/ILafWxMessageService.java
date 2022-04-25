package com.ruoyi.wx.service;

import com.ruoyi.wx.domain.LafWxMessage;

import java.util.List;

/**
 * 我的消息Service接口
 *
 * @author yang
 * @date 2022-04-21
 */
public interface ILafWxMessageService {
    /**
     * 查询我的消息列表
     *
     * @param id 用户身份标识
     * @return 我的消息集合
     */
    public List<LafWxMessage> selectLafWxMessageList(Long id);
}
