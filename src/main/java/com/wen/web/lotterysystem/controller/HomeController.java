package com.wen.web.lotterysystem.controller;

import com.wen.web.lotterysystem.configuration.SystemConfig;
import com.wen.web.lotterysystem.data.entity.MenuDO;
import com.wen.web.lotterysystem.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author admin
 * @date 2018-10-29 11:43
 */
@Controller
public class HomeController extends BaseController {
    @Autowired
    SystemConfig systemConfig;


    /*@GetMapping({"/", "/index", "/home"})
    public String root(Model model) {
        model.addAttribute("systemName", systemConfig.getSystemName());
        return "login";
    }*/

    @GetMapping({"/", "/login", "/home"})
    public String login(Model model) {
        model.addAttribute("pageName", systemConfig.getSystemName());
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        //TODO 加载菜单
        //List<Tree<MenuDO>> menus =
        return null;
    }
}
