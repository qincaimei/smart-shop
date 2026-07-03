package com.glu.smartshop.mapper;

import com.glu.smartshop.entity.Role;
import com.glu.smartshop.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface UserMapper {

    // 增加DISTINCT 去除联表查询产生的重复用户数据
    @Select("SELECT DISTINCT u.*, r.role FROM t_user u " +
            "LEFT JOIN t_user_role ur ON u.id = ur.user_id " +
            "LEFT JOIN t_role r ON ur.role_id = r.id")
    List<User> findAllWithRoles();

    // 原有代码不动
    @Select("SELECT * FROM t_user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT r.* FROM t_role r " +
            "LEFT JOIN t_user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}