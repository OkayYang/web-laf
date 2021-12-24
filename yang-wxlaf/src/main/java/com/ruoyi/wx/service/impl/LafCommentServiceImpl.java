package com.ruoyi.wx.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.wx.domain.LafStudent;
import com.ruoyi.wx.mapper.LafStudentMapper;
import com.ruoyi.wx.util.CommentDetail;
import com.ruoyi.wx.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.wx.mapper.LafCommentMapper;
import com.ruoyi.wx.domain.LafComment;
import com.ruoyi.wx.service.ILafCommentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 帖子留言Service业务层处理
 * 
 * @author yang
 * @date 2021-12-22
 */
@Service
public class LafCommentServiceImpl implements ILafCommentService
{
    @Autowired
    private LafCommentMapper lafCommentMapper;
    @Autowired
    private LafStudentMapper lafStudentMapper;

    /**
     * 查询帖子留言
     * 
     * @param comId 帖子留言主键
     * @return 帖子留言
     */
    @Override
    public LafComment selectLafCommentByComId(Long comId)
    {
        return lafCommentMapper.selectLafCommentByComId(comId);
    }

    /**
     * 查询帖子留言列表
     * 
     * @param lafComment 帖子留言
     * @return 帖子留言
     */
    @Override
    public List<LafComment> selectLafCommentList(LafComment lafComment)
    {
        return lafCommentMapper.selectLafCommentList(lafComment);
    }

    /**
     * 新增帖子留言
     * 
     * @param lafComment 帖子留言
     * @return 结果
     */
    @Override
    public int insertLafComment(LafComment lafComment)
    {
        lafComment.setCreateTime(DateUtils.getNowDate());
        return lafCommentMapper.insertLafComment(lafComment);
    }

    /**
     * 修改帖子留言
     * 
     * @param lafComment 帖子留言
     * @return 结果
     */
    @Override
    public int updateLafComment(LafComment lafComment)
    {
        return lafCommentMapper.updateLafComment(lafComment);
    }

    /**
     * 批量删除帖子留言
     * 
     * @param comIds 需要删除的帖子留言主键
     * @return 结果
     */
    @Override
    public int deleteLafCommentByComIds(String comIds)
    {
        return lafCommentMapper.deleteLafCommentByComIds(Convert.toStrArray(comIds));
    }

    /**
     * 删除帖子留言信息
     * 
     * @param comId 帖子留言主键
     * @return 结果
     */
    @Override
    public int deleteLafCommentByComId(Long comId)
    {
        return lafCommentMapper.deleteLafCommentByComId(comId);
    }

    /**
     * 查询帖子留言树列表
     * 
     * @return 所有帖子留言信息
     */
    @Override
    public List<Ztree> selectLafCommentTree()
    {
        List<LafComment> lafCommentList = lafCommentMapper.selectLafCommentList(new LafComment());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (LafComment lafComment : lafCommentList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(lafComment.getComId());
            ztree.setpId(lafComment.getParaentId());
            ztree.setName(lafComment.getComContent());
            ztree.setTitle(lafComment.getComContent());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 通过帖子ID查看评论
     * @param relID
     * @return
     */

    @Override
    public List<CommentDetail> selectLafCommentByTid(Long relID) {
        LafComment lafComment = new LafComment();
        lafComment.setComRelId(relID);
        List<LafComment> commentList = lafCommentMapper.selectWxLafCommentList(lafComment);
        List<CommentDetail> commentDetailList = new ArrayList<>();
        for (LafComment item:commentList
             ) {
            LafStudent student1 = lafStudentMapper.selectLafStudentByStuId(item.getComStuId());
            User user1 = new User(student1.getStuImage(),student1.getStuNick(),student1.getStuSex(),student1.getStuId());

            LafComment comment = lafCommentMapper.selectLafCommentByComId(item.getParaentId());
            Long replyUid = null;
            User user2 = new User();
            if (comment!=null){
                replyUid = comment.getComStuId();
                LafStudent student2 = lafStudentMapper.selectLafStudentByStuId(replyUid);
                user2.setAvatar(student2.getStuImage());
                user2.setNickName(student2.getStuNick());
                user2.setGender(student2.getStuSex());
                user2.setUid(student2.getStuId());
            }
            commentDetailList.add(new CommentDetail(item,user1,user2));
        }
        return commentDetailList;
    }
}
