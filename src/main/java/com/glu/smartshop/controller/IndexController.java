package com.glu.smartshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 主页 - 包含顶部导航和左侧菜单
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 欢迎页 - 在 iframe 中显示
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    // 登录页
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}