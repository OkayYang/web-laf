package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafCategory;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 物品种类Service接口
 * 
 * @author yang
 * @date 2021-10-08
 */
public interface ILafCategoryService 
{
    /**
     * 查询物品种类
     * 
     * @param cateId 物品种类主键
     * @return 物品种类
     */
    public LafCategory selectLafCategoryByCateId(Long cateId);

    /**
     * 查询物品种类列表
     * 
     * @param lafCategory 物品种类
     * @return 物品种类集合
     */
    public List<LafCategory> selectLafCategoryList(LafCategory lafCategory);

    /**
     * 新增物品种类
     * 
     * @param lafCategory 物品种类
     * @return 结果
     */
    public int insertLafCategory(LafCategory lafCategory);

    /**
     * 修改物品种类
     * 
     * @param lafCategory 物品种类
     * @return 结果
     */
    public int updateLafCategory(LafCategory lafCategory);

    /**
     * 批量删除物品种类
     * 
     * @param cateIds 需要删除的物品种类主键集合
     * @return 结果
     */
    public int deleteLafCategoryByCateIds(String cateIds);

    /**
     * 删除物品种类信息
     * 
     * @param cateId 物品种类主键
     * @return 结果
     */
    public int deleteLafCategoryByCateId(Long cateId);

    /**
     * 查询物品种类树列表
     * 
     * @return 所有物品种类信息
     */
    public List<Ztree> selectLafCategoryTree();
}
