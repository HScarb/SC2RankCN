package com.scarb.controller;

import com.scarb.model.Comment;
import com.scarb.model.User;
import com.scarb.service.CommentService;
import com.scarb.service.UserSerivce;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;

/**
 * Created by Scarb on 10/5/2016.
 */
@Controller
public class CommentsController {

    private static Logger logger = LoggerFactory.getLogger(CommentsController.class);

    @Resource
    private UserSerivce userSerivce;
    @Resource
    private CommentService commentService;

    @RequestMapping("comments")
    public String comments(Model model){
        model.addAttribute("currentPage", "comments");
        return "comments";
    }

    @RequestMapping("showcomments")
    public ModelAndView showComments(Integer currentPage, HttpSession session){
        ModelAndView mav = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated())
        {
            logger.debug("CurrentUser is Authenticated.");
            logger.debug((String) currentUser.getPrincipal());
        }
        mav.setViewName("comments");

        return mav;
    }

    @RequestMapping("leavecomment")
    public ModelAndView leaveComments(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        Comment comment = new Comment();
        // 获取当前登录的用户
        Subject currentUser = SecurityUtils.getSubject();
        if(!currentUser.isAuthenticated())  // 如果该用户没有登录，定向到登录页
        {
            mav.setViewName("login");
            return mav;
        }

        User cUser = userSerivce.findUserByLoginName((String) currentUser.getPrincipal());      // 用userService找到该User
        ;       // 获得留言内容
        comment.setCommentAuthorid(cUser.getUserId());                  // 设置消息的作者id
        comment.setCommentContent(request.getParameter("CommentWords")); // 设置消息的内容
        comment.setCommentTime(String.valueOf(System.currentTimeMillis()));     // 设置消息的发送时间
        comment.setCommentParentid(0);
        commentService.leaveComment(comment);

        mav.setViewName("redirect:/comments");
        return mav;
    }
}
