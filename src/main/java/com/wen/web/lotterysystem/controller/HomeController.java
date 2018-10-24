package com.wen.web.lotterysystem.controller;

import com.wen.web.lotterysystem.configuration.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author admin
 * @date 2018-10-29 11:43
 */
@Controller
public class HomeController extends BaseController {
    @Autowired
    SystemConfig systemConfig;


    @GetMapping({"/", "/index", "/home"})
    public String root(Model model){
        model.addAttribute("systemName",systemConfig.getSystemName());
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("pageName",systemConfig.getPageName());
        return "login";
    }
}
