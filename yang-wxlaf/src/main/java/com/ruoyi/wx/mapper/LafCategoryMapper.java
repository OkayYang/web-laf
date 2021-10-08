package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafCategory;

/**
 * 物品种类Mapper接口
 * 
 * @author yang
 * @date 2021-10-08
 */
public interface LafCategoryMapper 
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
     * 删除物品种类
     * 
     * @param cateId 物品种类主键
     * @return 结果
     */
    public int deleteLafCategoryByCateId(Long cateId);

    /**
     * 批量删除物品种类
     * 
     * @param cateIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafCategoryByCateIds(String[] cateIds);
}
