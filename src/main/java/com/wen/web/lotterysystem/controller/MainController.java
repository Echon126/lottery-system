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
        model.addAttribute("menus", this.getSecurityContext().getMenus());
        model.addAttribute("name", this.getSecurityContext().getUserDO().getName());
       /* FileDO fileDO = fileService.get(userDO.getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }*/
        model.addAttribute("username", this.getSecurityContext().getUserDO().getUsername());
        return "main";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }
}
