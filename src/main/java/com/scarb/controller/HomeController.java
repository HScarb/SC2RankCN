package com.scarb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Scarb on 9/14/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/test")
    public String Test(){
        return "test";
    }
}
