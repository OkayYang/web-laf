package com.ruoyi.wx.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafCategoryMapper;
import com.ruoyi.wx.domain.LafCategory;
import com.ruoyi.wx.service.ILafCategoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物品种类Service业务层处理
 * 
 * @author yang
 * @date 2021-10-08
 */
@Service
public class LafCategoryServiceImpl implements ILafCategoryService 
{
    @Autowired
    private LafCategoryMapper lafCategoryMapper;

    /**
     * 查询物品种类
     * 
     * @param cateId 物品种类主键
     * @return 物品种类
     */
    @Override
    public LafCategory selectLafCategoryByCateId(Long cateId)
    {
        return lafCategoryMapper.selectLafCategoryByCateId(cateId);
    }

    /**
     * 查询物品种类列表
     * 
     * @param lafCategory 物品种类
     * @return 物品种类
     */
    @Override
    public List<LafCategory> selectLafCategoryList(LafCategory lafCategory)
    {
        return lafCategoryMapper.selectLafCategoryList(lafCategory);
    }

    /**
     * 新增物品种类
     * 
     * @param lafCategory 物品种类
     * @return 结果
     */
    @Override
    public int insertLafCategory(LafCategory lafCategory)
    {
        lafCategory.setCreateTime(DateUtils.getNowDate());
        return lafCategoryMapper.insertLafCategory(lafCategory);
    }

    /**
     * 修改物品种类
     * 
     * @param lafCategory 物品种类
     * @return 结果
     */
    @Override
    public int updateLafCategory(LafCategory lafCategory)
    {
        return lafCategoryMapper.updateLafCategory(lafCategory);
    }

    /**
     * 批量删除物品种类
     * 
     * @param cateIds 需要删除的物品种类主键
     * @return 结果
     */
    @Override
    public int deleteLafCategoryByCateIds(String cateIds)
    {
        return lafCategoryMapper.deleteLafCategoryByCateIds(Convert.toStrArray(cateIds));
    }

    /**
     * 删除物品种类信息
     * 
     * @param cateId 物品种类主键
     * @return 结果
     */
    @Override
    public int deleteLafCategoryByCateId(Long cateId)
    {
        return lafCategoryMapper.deleteLafCategoryByCateId(cateId);
    }

    /**
     * 查询物品种类树列表
     * 
     * @return 所有物品种类信息
     */
    @Override
    public List<Ztree> selectLafCategoryTree()
    {
        List<LafCategory> lafCategoryList = lafCategoryMapper.selectLafCategoryList(new LafCategory());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (LafCategory lafCategory : lafCategoryList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(lafCategory.getCateId());
            ztree.setpId(lafCategory.getParaentId());
            ztree.setName(lafCategory.getCateName());
            ztree.setTitle(lafCategory.getCateName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
