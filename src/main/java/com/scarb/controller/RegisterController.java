package com.scarb.controller;

import com.scarb.model.UserExample;
import com.scarb.service.RegisterService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register/checkUserName", method = RequestMethod.POST)
    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userName = (String) request.getParameter("userName");
        // 检测同户名是否存在
        UserExample userExample = new UserExample();
        UserExample.Criteria conditionCri = userExample.createCriteria();
        conditionCri.andUserNameEqualTo(userName);
        int num = registerService.countByExample(userExample);
        // 用户名是否存在的标志
        boolean flag = false;
        if(num > 0){flag = false;}
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
}
