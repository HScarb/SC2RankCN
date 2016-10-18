package com.scarb.service;

import com.scarb.model.Comment;
import com.scarb.util.PagedResult;

/**
 * Created by Scarb on 10/18/2016.
 */
public interface CommentService {

    /**
     * 根据页码和每页结果数量显示所有的留言
     * @param pageNo 页码
     * @param pageSize 每页显示数量
     * @return
     */
    public PagedResult<Comment> showComments(Integer pageNo, Integer pageSize);

    /**
     * 返回评论数
     * @return
     */
    public int commentsCount();

    /**
     * 留言
     * @param comment
     * @return
     */
    public boolean leaveComment(Comment comment);

    /**
     * 根据留言id删除留言
     * @param commentID
     * @return
     */
    public boolean deleteComment(int commentID);
}
