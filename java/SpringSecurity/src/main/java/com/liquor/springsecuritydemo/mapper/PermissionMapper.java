package com.liquor.springsecuritydemo.mapper;

import com.liquor.springsecuritydemo.model.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:13
 */
@Repository
public interface PermissionMapper {

    @Select("select * from permission where userId = #{userId}")
    List<Permission> selectByUserId(Long userId);
}
