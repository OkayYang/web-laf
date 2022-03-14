package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafApiToken;

/**
 * ApiTokenService接口
 * 
 * @author yang
 * @date 2022-03-10
 */
public interface ILafApiTokenService 
{
    /**
     * 查询ApiToken
     * 
     * @param id ApiToken主键
     * @return ApiToken
     */
    public LafApiToken selectLafApiTokenById(Long id);

    /**
     * 查询ApiToken列表
     * 
     * @param lafApiToken ApiToken
     * @return ApiToken集合
     */
    public List<LafApiToken> selectLafApiTokenList(LafApiToken lafApiToken);

    /**
     * 新增ApiToken
     * 
     * @param lafApiToken ApiToken
     * @return 结果
     */
    public int insertLafApiToken(LafApiToken lafApiToken);

    /**
     * 修改ApiToken
     * 
     * @param lafApiToken ApiToken
     * @return 结果
     */
    public int updateLafApiToken(LafApiToken lafApiToken);

    /**
     * 批量删除ApiToken
     * 
     * @param ids 需要删除的ApiToken主键集合
     * @return 结果
     */
    public int deleteLafApiTokenByIds(String ids);

    /**
     * 删除ApiToken信息
     * 
     * @param id ApiToken主键
     * @return 结果
     */
    public int deleteLafApiTokenById(Long id);
}
