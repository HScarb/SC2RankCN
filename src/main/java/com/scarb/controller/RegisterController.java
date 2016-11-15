package com.scarb.controller;

import com.scarb.model.User;
import com.scarb.model.UserExample;
import com.scarb.model.UserExample.Criteria;
import com.scarb.service.RegisterService;
import com.scarb.util.CipherUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scarb on 9/27/2016.
 */
@Controller
public class RegisterController {
    private static Logger logger = Logger.getLogger(RegisterController.class);

    @Resource
    private RegisterService registerService;

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("currentPage", "register");
        return "register";
    }

    @RequestMapping(value = "/register/checkUserName", method = RequestMethod.POST)
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userName = (String) request.getParameter("username");
        // 检测同户名是否存在
        UserExample userExample = new UserExample();
        Criteria conditionCri = userExample.createCriteria();
        conditionCri.andUserNameEqualTo(userName);      // 添加判断 USER_NAME = userName
        int num = registerService.countByExample(userExample);      // 统计符合判断的userExample
        // 用户名是否存在的标志
        boolean flag = false;
        if(num > 0){flag = true;}
        // 将数据转换成json
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", flag);
        String json = JSONObject.fromObject(map).toString();
        // 将数据返回
        response.setCharacterEncoding("UTF-8");
        response.flushBuffer();
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
        return null;
    }

    @RequestMapping(value = "/register/checkEmail", method = RequestMethod.POST)
    public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String email = (String) request.getParameter("email");
        // 检验邮箱是否存在
        UserExample userExample = new UserExample();
        Criteria conditionCri = userExample.createCriteria();
        conditionCri.andUserEmailEqualTo(email);
        int num = registerService.countByExample(userExample);
        // 邮箱是否存在的标志
        boolean flag = false;
        if (num > 0){
            flag = true;
        }
        // 将数据转换成json
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flag", flag);
        String json = JSONObject.fromObject(map).toString();
        // 将数据返回
        response.setCharacterEncoding("UTF-8");
        response.flushBuffer();
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
        return null;
    }

    @RequestMapping(value = "/register/succeed")
    public ModelAndView succeed(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String username = (String) request.getParameter("username");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        if(username == null || email == null || password==null){
            return new ModelAndView("redirect:/register");
        }

        // 检测同户名是否存在
        UserExample userExample = new UserExample();
        Criteria conditionCri = userExample.createCriteria();
        conditionCri.andUserNameEqualTo(username);      // 添加判断 USER_NAME = userName
        int num = registerService.countByExample(userExample);      // 统计符合判断的userExample
        // 用户名是否存在的标志
        if(num <= 0){   // 小于0即用户名不存在
            // 如果用户名不存在，新增用户插入数据库
            User user = new User();
            user.setUserName(username);
            user.setUserEmail(email);
            user.setUserPassword(password);
            registerService.insert(user);
        }


        // 将数据转换成
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("email", email);
        map.put("password", password);

        // 注册完默认登录
        String pwd = CipherUtil.generatePassword(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            // 使用shiro来验证
            if(!currentUser.isAuthenticated()){
                token.setRememberMe(true);
                currentUser.login(token);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }

        return new ModelAndView("registerSucceed", map);
    }
}
