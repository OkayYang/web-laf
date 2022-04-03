package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafAnnounceMapper;
import com.ruoyi.wx.domain.LafAnnounce;
import com.ruoyi.wx.service.ILafAnnounceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 小程序公告Service业务层处理
 * 
 * @author yang
 * @date 2022-03-25
 */
@Service
public class LafAnnounceServiceImpl implements ILafAnnounceService 
{
    @Autowired
    private LafAnnounceMapper lafAnnounceMapper;

    /**
     * 查询小程序公告
     * 
     * @param annId 小程序公告主键
     * @return 小程序公告
     */
    @Override
    public LafAnnounce selectLafAnnounceByAnnId(Long annId)
    {
        return lafAnnounceMapper.selectLafAnnounceByAnnId(annId);
    }

    /**
     * 查询小程序公告列表
     * 
     * @param lafAnnounce 小程序公告
     * @return 小程序公告
     */
    @Override
    public List<LafAnnounce> selectLafAnnounceList(LafAnnounce lafAnnounce)
    {
        return lafAnnounceMapper.selectLafAnnounceList(lafAnnounce);
    }

    /**
     * 新增小程序公告
     * 
     * @param lafAnnounce 小程序公告
     * @return 结果
     */
    @Override
    public int insertLafAnnounce(LafAnnounce lafAnnounce)
    {
        lafAnnounce.setCreateTime(DateUtils.getNowDate());
        return lafAnnounceMapper.insertLafAnnounce(lafAnnounce);
    }

    /**
     * 修改小程序公告
     * 
     * @param lafAnnounce 小程序公告
     * @return 结果
     */
    @Override
    public int updateLafAnnounce(LafAnnounce lafAnnounce)
    {
        return lafAnnounceMapper.updateLafAnnounce(lafAnnounce);
    }

    /**
     * 批量删除小程序公告
     * 
     * @param annIds 需要删除的小程序公告主键
     * @return 结果
     */
    @Override
    public int deleteLafAnnounceByAnnIds(String annIds)
    {
        return lafAnnounceMapper.deleteLafAnnounceByAnnIds(Convert.toStrArray(annIds));
    }

    /**
     * 删除小程序公告信息
     * 
     * @param annId 小程序公告主键
     * @return 结果
     */
    @Override
    public int deleteLafAnnounceByAnnId(Long annId)
    {
        return lafAnnounceMapper.deleteLafAnnounceByAnnId(annId);
    }

    /**
     * 更新浏览量
     * @return
     */
    @Override
    public int updateBrowse(Long annId){
        return lafAnnounceMapper.updateBrowse(annId);
    }
}
