package com.glu.smartshop.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Product implements Serializable {
    private Integer id;
    private String name;
    private String photoUrl;   // 对应数据库 photo_url
    private Double price;
    private String descp;
    private LocalDate releaseDate;
    private Integer catId;     // 对应数据库 cat_id
    private static final long serialVersionUID = 1L;
    // 关联的分类名称（用于页面展示）
    private String categoryName;  // 这个字段数据库中不存在，是联查出来的
}