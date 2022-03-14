package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafApiToken;

/**
 * ApiTokenMapper接口
 * 
 * @author yang
 * @date 2022-03-10
 */
public interface LafApiTokenMapper 
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
     * 删除ApiToken
     * 
     * @param id ApiToken主键
     * @return 结果
     */
    public int deleteLafApiTokenById(Long id);

    /**
     * 批量删除ApiToken
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafApiTokenByIds(String[] ids);
}
