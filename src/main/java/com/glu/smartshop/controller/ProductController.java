package com.glu.smartshop.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glu.smartshop.entity.Category;
import com.glu.smartshop.entity.Product;
import com.glu.smartshop.mapper.ProductMapper;
import com.glu.smartshop.service.CategoryService;
import com.glu.smartshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "6") Integer pageSize,
            @RequestParam(required = false) Integer catId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model
    ){
        PageInfo<Product> pageInfo = productService.searchProducts(catId,name,minPrice,maxPrice,pageNum,pageSize);
        List<Category> categoryList = categoryService.findAll();

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("categories", categoryList);
        model.addAttribute("catId", catId);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        return "product/list";
    }

    // 跳转新增页面
    @GetMapping("/add")
    public String toAddPage(Model model){
        model.addAttribute("categories",categoryService.findAll());
        return "product/add";
    }

    // 【重点修改】适配路径传参 /product/edit/{id}，前端通用格式
    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        Product product = productService.getById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryList);
        return "product/edit";
    }

    @GetMapping("/edit")
    public String toEditPageWithParam(@RequestParam("id") Integer id, Model model){
        Product product = productService.getById(id);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryList);
        return "product/edit";
    }

    // 保存新增商品
    @PostMapping("/save")
    public String saveProduct(Product product){
        productMapper.insert(product);
        return "redirect:/product/list";
    }

    // 修改提交接口
    @PostMapping("/update")
    public String updateProduct(Product product){
        productService.update(product);
        return "redirect:/product/list";
    }
}