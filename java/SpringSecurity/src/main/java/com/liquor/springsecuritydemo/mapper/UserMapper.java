package com.liquor.springsecuritydemo.mapper;

import com.liquor.springsecuritydemo.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:12
 */
@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User getByUsername(String username);
}
