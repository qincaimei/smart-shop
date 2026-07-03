package com.glu.smartshop.controller;

import com.github.pagehelper.PageInfo;
import com.glu.smartshop.entity.User;
import com.glu.smartshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 测试接口（保留不动）
    @GetMapping("/getUser/admin")
    public User getUserAdmin(String username) {
        return (User) userService.loadUserByUsername(username);
    }

    @GetMapping("/user/list")
    public String userList(Model model) {
        List<User> userList = userService.findAllUsers();
        System.out.println("👥 查询到用户数: " + (userList != null ? userList.size() : 0));
        // 封装分页对象
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        // 前端页面取值：pageInfo，名称完全对应
        model.addAttribute("pageInfo", pageInfo);
        return "user/list";
    }
}