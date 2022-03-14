package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafApiTokenMapper;
import com.ruoyi.wx.domain.LafApiToken;
import com.ruoyi.wx.service.ILafApiTokenService;
import com.ruoyi.common.core.text.Convert;

/**
 * ApiTokenService业务层处理
 * 
 * @author yang
 * @date 2022-03-10
 */
@Service
public class LafApiTokenServiceImpl implements ILafApiTokenService 
{
    @Autowired
    private LafApiTokenMapper lafApiTokenMapper;

    /**
     * 查询ApiToken
     * 
     * @param id ApiToken主键
     * @return ApiToken
     */
    @Override
    public LafApiToken selectLafApiTokenById(Long id)
    {
        return lafApiTokenMapper.selectLafApiTokenById(id);
    }

    /**
     * 查询ApiToken列表
     * 
     * @param lafApiToken ApiToken
     * @return ApiToken
     */
    @Override
    public List<LafApiToken> selectLafApiTokenList(LafApiToken lafApiToken)
    {
        return lafApiTokenMapper.selectLafApiTokenList(lafApiToken);
    }

    /**
     * 新增ApiToken
     * 
     * @param lafApiToken ApiToken
     * @return 结果
     */
    @Override
    public int insertLafApiToken(LafApiToken lafApiToken)
    {
        lafApiToken.setCreateTime(DateUtils.getNowDate());
        return lafApiTokenMapper.insertLafApiToken(lafApiToken);
    }

    /**
     * 修改ApiToken
     * 
     * @param lafApiToken ApiToken
     * @return 结果
     */
    @Override
    public int updateLafApiToken(LafApiToken lafApiToken)
    {
        return lafApiTokenMapper.updateLafApiToken(lafApiToken);
    }

    /**
     * 批量删除ApiToken
     * 
     * @param ids 需要删除的ApiToken主键
     * @return 结果
     */
    @Override
    public int deleteLafApiTokenByIds(String ids)
    {
        return lafApiTokenMapper.deleteLafApiTokenByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除ApiToken信息
     * 
     * @param id ApiToken主键
     * @return 结果
     */
    @Override
    public int deleteLafApiTokenById(Long id)
    {
        return lafApiTokenMapper.deleteLafApiTokenById(id);
    }
}
