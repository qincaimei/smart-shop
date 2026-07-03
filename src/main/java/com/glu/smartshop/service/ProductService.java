package com.glu.smartshop.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glu.smartshop.entity.Product;
import com.glu.smartshop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ProductMapper productMapper;



    // 手动管理缓存的方法示例
    public Product getProductWithRedis(Integer id) {
        String key = "product:" + id;

        // 1. 先查 Redis
        Product product = (Product) redisTemplate.opsForValue().get(key);
        if (product != null) {
            System.out.println("✅ 从 Redis 获取数据，id=" + id);
            return product;
        }

        // 2. Redis 没有，查 MySQL
        product = productMapper.findByIdWithCategory(id);
        if (product != null) {
            // 存入 Redis，设置过期时间 30 分钟
            redisTemplate.opsForValue().set(key, product, 30, TimeUnit.MINUTES);
            System.out.println("📥 从 MySQL 查询并写入 Redis，id=" + id);
        }

        return product;
    }

    // ====== 根据ID查询（带缓存） ======
    @Cacheable(value = "product", key = "#id")
    public Product getById(Integer id) {
        return productMapper.findByIdWithCategory(id);
    }

    // ====== 更新商品（更新缓存） ======
    @CachePut(value = "product", key = "#product.id")
    public Product update(Product product) {
        productMapper.update(product);
        return product;
    }

    // ====== 删除商品（清除缓存） ======
    @CacheEvict(value = "product", key = "#id")
    public void deleteById(Integer id) {
        productMapper.deleteById(id);
    }

    // ====== 分页搜索商品 ======
    public PageInfo<Product> searchProducts(Integer catId, String name, Double minPrice, Double maxPrice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.searchProducts(catId, name, minPrice, maxPrice);
        return new PageInfo<>(products);
    }


    }
