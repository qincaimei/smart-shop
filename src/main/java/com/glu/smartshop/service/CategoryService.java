package com.glu.smartshop.service;

import com.glu.smartshop.entity.Category;
import com.glu.smartshop.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    // 新增
    public void save(Category category){
        categoryMapper.insert(category);
    }
    // 更新
    public void updateById(Category category){
        categoryMapper.updateById(category);
    }
    // 删除
    public void removeById(Integer id){
        categoryMapper.deleteById(id);
    }
    // 适配控制器getById调用
    public Category getById(Integer id){
        return this.findById(id);
    }
}