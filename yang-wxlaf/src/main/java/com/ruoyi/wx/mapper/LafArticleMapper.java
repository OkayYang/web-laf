package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafArticle;

/**
 * articleMapper接口
 * 
 * @author yang
 * @date 2022-05-09
 */
public interface LafArticleMapper 
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
     * 删除article
     * 
     * @param id article主键
     * @return 结果
     */
    public int deleteLafArticleById(Long id);

    /**
     * 批量删除article
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafArticleByIds(String[] ids);
}
