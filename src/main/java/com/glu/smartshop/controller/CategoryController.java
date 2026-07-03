package com.glu.smartshop.controller;

import com.github.pagehelper.PageInfo;
import com.glu.smartshop.entity.Category;
import com.glu.smartshop.mapper.CategoryMapper;
import com.glu.smartshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    // 补上关键：注入分类业务层，解决categoryService红色报错
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryMapper.findAll();
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        model.addAttribute("pageInfo", pageInfo);
        return "category/list";
    }

    // 修复路径重复，只写/add即可
    @GetMapping("/add")
    public String toAddPage(){
        return "category/add";
    }

    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        Category category = categoryService.getById(id);
        model.addAttribute("category",category);
        return "category/edit";
    }

    @PostMapping("/save")
    public String saveCategory(Category category){
        categoryService.save(category);
        return "redirect:/category/list";
    }

    @PostMapping("/update")
    public String updateCategory(Category category){
        categoryService.updateById(category);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        categoryService.removeById(id);
        return "redirect:/category/list";
    }
}