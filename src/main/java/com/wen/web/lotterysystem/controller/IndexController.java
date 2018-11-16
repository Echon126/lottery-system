package com.wen.web.lotterysystem.controller;

import com.wen.web.lotterysystem.configuration.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户登录
 *
 * @author admin
 * @date 2018-11-16 14:06
 */
@Controller
public class IndexController extends BaseController {
    @Autowired
    SystemConfig systemConfig;

    @GetMapping({"/", "login"})
    public String index(Model model) {
        model.addAttribute("pageName", systemConfig.getSystemName());
        return "login";
    }
}
