package com.scarb.controller;

import com.scarb.model.Player;
import com.scarb.service.LadderService;
import com.scarb.service.PlayerService;
import com.scarb.util.PagedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Scarb on 9/14/2016.
 */
@Controller
public class PlayerController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PlayerService playerService;

    @RequestMapping("/")
    public String getIndex(Model model){
//        ModelAndView mav = new ModelAndView("index");
//        Player player = playerService.selectPlayerById(1);
//        mav.addObject("player", player);
//        return mav;
        model.addAttribute("currentPage", "index");
        return "index";
    }

    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("currentPage", "about");
        return "about";
    }

    @RequestMapping("/grandmaster")
    public String grandmaster(Model model){
        model.addAttribute("currentPage", "index");
        return "grandmaster";
    }
    @RequestMapping("/master")
    public String master(Model model){
        model.addAttribute("currentPage", "index");
        return "master";
    }
    @RequestMapping("/diamond")
    public String diamond(Model model){
        model.addAttribute("currentPage", "index");
        return "diamond";
    }
    @RequestMapping("platinum")
    public String platinum(Model model){
        model.addAttribute("currentPage", "index");
        return "platinum";
    }
    @RequestMapping("gold")
    public String gold(Model model){
        model.addAttribute("currentPage", "index");
        return "gold";
    }
    @RequestMapping("silver")
    public String silver(Model model){
        model.addAttribute("currentPage", "index");
        return "silver";
    }
    @RequestMapping("bronze")
    public String bronze(Model model){
        model.addAttribute("currentPage", "index");
        return "bronze";
    }
    @RequestMapping("noleague")
    public String noleague(Model model){
        model.addAttribute("currentPage", "index");
        return "noleague";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")      // springmvc 发送ajax中文乱码的几种解决办法 http://wxynxyo.iteye.com/blog/2000327
    @ResponseBody
    public String list(Integer pageNumber, Integer pageSize, String name){
        logger.info("分页查询玩家信息列表请求入参：pageNumber{}, pageSize{}", pageNumber, pageSize);
        try {
            PagedResult<Player> pageResult = playerService.queryNameByPage(name, pageNumber, pageSize);
            return responseSuccess(pageResult);
        } catch (Exception e) {
            return responseFail(e.getMessage());
        }
    }

    @RequestMapping(value = "/listleague", method = RequestMethod.POST,produces = "text/html;charset=UTF-8")      // springmvc 发送ajax中文乱码的几种解决办法 http://wxynxyo.iteye.com/blog/2000327
    @ResponseBody
    public String listByLeague(Integer pageNumber, Integer pageSize, String league){
        logger.info("分页查询玩家信息列表请求入参：pageNumber{}, pageSize{}", pageNumber, pageSize);
        try {
            PagedResult<Player> pageResult = playerService.queryLeagueByPage(league, pageNumber, pageSize);
            return responseSuccess(pageResult);
        } catch (Exception e) {
            return responseFail(e.getMessage());
        }
    }
}
