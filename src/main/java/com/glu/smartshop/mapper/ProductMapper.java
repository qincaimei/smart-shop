package com.glu.smartshop.mapper;

import com.glu.smartshop.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {

    // 多表联查：商品信息 + 分类名称
    @Select("SELECT p.*, c.name as category_name FROM product p " +
            "LEFT JOIN category c ON p.cat_id = c.id " +
            "ORDER BY p.id")
    List<Product> findAllWithCategory();

    // 根据ID查询单个商品
    @Select("SELECT p.*, c.name as category_name FROM product p " +
            "LEFT JOIN category c ON p.cat_id = c.id " +
            "WHERE p.id = #{id}")
    Product findByIdWithCategory(@Param("id") Integer id);

    // ====== 阶段三要用：动态条件查询 ======
    // 注意：动态SQL写在XML里，这里只定义方法
    List<Product> searchProducts(@Param("catId") Integer catId,
                                 @Param("name") String name,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);

    // ====== 新增：更新商品 ======
    @Update("UPDATE product SET " +
            "name = #{name}, " +
            "price = #{price}, " +
            "cat_id = #{catId}, " +
            "stock = #{stock}, " +
            "description = #{description}, " +
            "update_time = NOW() " +
            "WHERE id = #{id}")
    int update(Product product);

    // ====== 新增：删除商品 ======
    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);

    void insert(Product product);
}