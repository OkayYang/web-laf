package com.ruoyi.wx.service.impl;

import com.ruoyi.wx.domain.LafWxMessage;
import com.ruoyi.wx.mapper.LafWxMessageMapper;
import com.ruoyi.wx.service.ILafWxMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 我的消息Service业务层处理
 *
 * @author yang
 * @date 2022-04-21
 */
@Service
public class ILafWxMessageServiceImpl implements ILafWxMessageService {

    @Autowired
    private LafWxMessageMapper lafWxMessageMapper;

    @Override
    public List<LafWxMessage> selectLafWxMessageList(Long id) {
        return  lafWxMessageMapper.selectLafWxMessageList(id);
    }
}
