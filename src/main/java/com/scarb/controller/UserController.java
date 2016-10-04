package com.scarb.controller;

import com.scarb.realm.ShiroDbRealm;
import com.scarb.service.UserSerivce;
import com.scarb.util.CipherUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Scarb on 10/2/2016.
 */
@Controller
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private UserSerivce userSerivce;

    /**
     * 初始登陆界面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String tologin(HttpServletRequest request, HttpServletResponse response, Model model){
        logger.debug("REQUEST FROM IP[" + request.getRemoteHost() + "]");
        model.addAttribute("currentPage", "login");
        return "login";
    }

    /**
     * 验证用户名和密码
     * @param request
     * @return
     */
    @RequestMapping("checkLogin")
    public String login(HttpServletRequest request){
        String result = "login";
        // 取得用户名
        String username = request.getParameter("username");
        // 取得密码，并用MD5加密
        String password = CipherUtil.generatePassword(request.getParameter("password"));
        // String password = request.getParameter("password:);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        Subject currentUser = SecurityUtils.getSubject();
        try {
            System.out.println("------------------------");
            // 使用shiro来验证
            if(!currentUser.isAuthenticated()){
                token.setRememberMe(true);
                currentUser.login(token);       // 验证角色和权限
            }
            System.out.println("result: " + result);
            result = "redirect:/";                   // 验证成功
        } catch (Exception e){
            logger.error(e.getMessage());
            request.setAttribute("errorInfo", "用户名或者密码错误");
            result = "login";                // 验证失败
        }
        return result;
    }

    /**
     * 退出
     * @return
     */
    /*@RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        String result = "index";
        currentUser.logout();
        return result;
    }*/
    @RequestMapping("logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/";
    }

    @RequestMapping("/shiro")
    public String shiroTest(Model model){
        model.addAttribute("currentPage", "shiro");
        return "shiro";
    }
}
