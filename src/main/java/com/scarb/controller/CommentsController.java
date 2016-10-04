package com.scarb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Scarb on 10/5/2016.
 */
@Controller
public class CommentsController {

    private static Logger logger = LoggerFactory.getLogger(CommentsController.class);

    @RequestMapping("comments")
    public String comments(Model model){
        model.addAttribute("currentPage", "comments");
        return "comments";
    }
}
