package com.ruoyi.wx.service;

import java.util.List;
import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.wx.util.commet.CommentDetail;

/**
 * 帖子留言Service接口
 * 
 * @author yang
 * @date 2021-12-22
 */
public interface ILafCommentService 
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
     * 批量删除帖子留言
     * 
     * @param comIds 需要删除的帖子留言主键集合
     * @return 结果
     */
    public int deleteLafCommentByComIds(String comIds);

    /**
     * 删除帖子留言信息
     * 
     * @param comId 帖子留言主键
     * @return 结果
     */
    public int deleteLafCommentByComId(Long comId);

    /**
     * 查询帖子留言树列表
     * 
     * @return 所有帖子留言信息
     */
    public List<Ztree> selectLafCommentTree();

    /**
     * 获取当前帖子下的评论
     */
    public List<CommentDetail> selectLafCommentByTid(Long relID);
}
