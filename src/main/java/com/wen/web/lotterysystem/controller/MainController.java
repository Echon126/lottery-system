package com.wen.web.lotterysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页面及菜单加载
 *
 * @author admin
 * @date 2018-11-16 10:14
 */
@Controller
public class MainController extends BaseController {

    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("username", this.getContext());
        return "main";
    }

}
