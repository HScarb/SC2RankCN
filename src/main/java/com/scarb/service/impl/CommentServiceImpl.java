package com.scarb.service.impl;

import com.github.pagehelper.PageHelper;
import com.scarb.dao.CommentMapper;
import com.scarb.model.Comment;
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
    public PagedResult<Comment> showComments(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize); // startPage告诉拦截器开始分页，分页参数是这两个
        return BeanUtil.toPagedResult(commentMapper.selectAll());
    }

    @Override
    public int commentsCount() {
        return 0;
    }

    @Override
    public boolean leaveComment(Comment comment) {
        return false;
    }

    @Override
    public boolean deleteComment(int commentID) {
        return false;
    }
}
