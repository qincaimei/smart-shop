package com.glu.smartshop.mapper;

import com.glu.smartshop.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category ORDER BY id")
    List<Category> findAll();

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(@Param("id") Integer id);

    // 新增分类
    @Insert("INSERT INTO category(name, descp) VALUES(#{name}, #{descp})")
    int insert(Category category);

    // 根据id修改
    @Update("UPDATE category SET name=#{name},descp=#{descp} WHERE id=#{id}")
    int updateById(Category category);

    // 根据id删除
    @Delete("DELETE FROM category WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);
}