package com.ruoyi.wx.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafArticleMapper;
import com.ruoyi.wx.domain.LafArticle;
import com.ruoyi.wx.service.ILafArticleService;
import com.ruoyi.common.core.text.Convert;

/**
 * articleService业务层处理
 * 
 * @author yang
 * @date 2022-05-09
 */
@Service
public class LafArticleServiceImpl implements ILafArticleService 
{
    @Autowired
    private LafArticleMapper lafArticleMapper;

    /**
     * 查询article
     * 
     * @param id article主键
     * @return article
     */
    @Override
    public LafArticle selectLafArticleById(Long id)
    {
        return lafArticleMapper.selectLafArticleById(id);
    }

    /**
     * 查询article列表
     * 
     * @param lafArticle article
     * @return article
     */
    @Override
    public List<LafArticle> selectLafArticleList(LafArticle lafArticle)
    {
        return lafArticleMapper.selectLafArticleList(lafArticle);
    }

    /**
     * 新增article
     * 
     * @param lafArticle article
     * @return 结果
     */
    @Override
    public int insertLafArticle(LafArticle lafArticle)
    {
        lafArticle.setCreateTime(DateUtils.getNowDate());
        return lafArticleMapper.insertLafArticle(lafArticle);
    }

    /**
     * 修改article
     * 
     * @param lafArticle article
     * @return 结果
     */
    @Override
    public int updateLafArticle(LafArticle lafArticle)
    {
        return lafArticleMapper.updateLafArticle(lafArticle);
    }

    /**
     * 批量删除article
     * 
     * @param ids 需要删除的article主键
     * @return 结果
     */
    @Override
    public int deleteLafArticleByIds(String ids)
    {
        return lafArticleMapper.deleteLafArticleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除article信息
     * 
     * @param id article主键
     * @return 结果
     */
    @Override
    public int deleteLafArticleById(Long id)
    {
        return lafArticleMapper.deleteLafArticleById(id);
    }
}
