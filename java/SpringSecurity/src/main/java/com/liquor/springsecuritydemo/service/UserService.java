package com.liquor.springsecuritydemo.service;

import com.liquor.springsecuritydemo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:12
 */
public interface UserService extends UserDetailsService {

    User getByUsername(String username);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
