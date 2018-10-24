package com.wen.web.lotterysystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author admin
 * @date 2018-10-29 11:43
 */
@Controller
public class UserController extends BaseController {
    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("username", this.getContext());
        return "user/user";
    }
}
