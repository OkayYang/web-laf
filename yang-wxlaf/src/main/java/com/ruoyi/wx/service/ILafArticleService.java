package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafArticle;

/**
 * articleService接口
 * 
 * @author yang
 * @date 2022-05-09
 */
public interface ILafArticleService 
{
    /**
     * 查询article
     * 
     * @param id article主键
     * @return article
     */
    public LafArticle selectLafArticleById(Long id);

    /**
     * 查询article列表
     * 
     * @param lafArticle article
     * @return article集合
     */
    public List<LafArticle> selectLafArticleList(LafArticle lafArticle);

    /**
     * 新增article
     * 
     * @param lafArticle article
     * @return 结果
     */
    public int insertLafArticle(LafArticle lafArticle);

    /**
     * 修改article
     * 
     * @param lafArticle article
     * @return 结果
     */
    public int updateLafArticle(LafArticle lafArticle);

    /**
     * 批量删除article
     * 
     * @param ids 需要删除的article主键集合
     * @return 结果
     */
    public int deleteLafArticleByIds(String ids);

    /**
     * 删除article信息
     * 
     * @param id article主键
     * @return 结果
     */
    public int deleteLafArticleById(Long id);
}
