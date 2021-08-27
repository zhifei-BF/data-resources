//package com.liquor.springsecuritydemo.service;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Service;
//
///**
// * @Author: Liquor.Huang
// * @Date 2021/8/27 15:43
// */
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
////        String hashpw = BCrypt.hashpw("123456",BCrypt.gensalt());
//        UserDetails userDetails = User.withUsername("liquor").password("{noop}123456").authorities("admin").build();
//        return userDetails;
//    }
//}
