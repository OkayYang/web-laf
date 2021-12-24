package com.ruoyi.wx.mapper;

import java.util.List;
import com.ruoyi.wx.domain.LafComment;

/**
 * 帖子留言Mapper接口
 * 
 * @author yang
 * @date 2021-12-22
 */
public interface LafCommentMapper 
{
    /**
     * 查询帖子留言
     * 
     * @param comId 帖子留言主键
     * @return 帖子留言
     */
    public LafComment selectLafCommentByComId(Long comId);

    /**
     * 查询帖子留言列表
     * 
     * @param lafComment 帖子留言
     * @return 帖子留言集合
     */
    public List<LafComment> selectLafCommentList(LafComment lafComment);

    /**
     * wx查询帖子留言列表
     *
     * @param lafComment 帖子留言
     * @return 帖子留言集合
     */
    public List<LafComment> selectWxLafCommentList(LafComment lafComment);

    /**
     * 新增帖子留言
     * 
     * @param lafComment 帖子留言
     * @return 结果
     */
    public int insertLafComment(LafComment lafComment);

    /**
     * 修改帖子留言
     * 
     * @param lafComment 帖子留言
     * @return 结果
     */
    public int updateLafComment(LafComment lafComment);

    /**
     * 删除帖子留言
     * 
     * @param comId 帖子留言主键
     * @return 结果
     */
    public int deleteLafCommentByComId(Long comId);

    /**
     * 批量删除帖子留言
     * 
     * @param comIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLafCommentByComIds(String[] comIds);
}
