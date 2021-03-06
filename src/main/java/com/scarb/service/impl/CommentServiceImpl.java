package com.scarb.service.impl;

import com.github.pagehelper.PageHelper;
import com.scarb.dao.CommentMapper;
import com.scarb.model.Comment;
import com.scarb.model.CommentExample;
import com.scarb.service.CommentService;
import com.scarb.util.BeanUtil;
import com.scarb.util.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Scarb on 10/18/2016.
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public PagedResult<Comment> showComments(CommentExample commentExample, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize); // startPage告诉拦截器开始分页，分页参数是这两个
        return BeanUtil.toPagedResult(commentMapper.selectByExample(commentExample));
    }

    @Override
    public int commentsCount() {
        return 0;
    }

    @Override
    public boolean leaveComment(Comment comment) {
        // 如果没有评论内容，不插入数据库
        if(comment.getCommentContent() == "" || comment.getCommentContent() == null)
            return false;
        // 将评论内容插入数据库
        int n = commentMapper.insertSelective(comment);     // 选择性插入
        if(n!=0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteComment(int commentID) {
        int n = commentMapper.deleteByPrimaryKey(commentID);
        if (n!=0)
            return true;
        else
            return false;
    }
}
