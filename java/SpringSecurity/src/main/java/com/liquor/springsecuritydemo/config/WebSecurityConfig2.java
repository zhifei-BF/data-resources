//package com.liquor.springsecuritydemo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @Author: Liquor.Huang
// * @Date 2021/8/27 16:07
// */
//@Configuration
//public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//                String pw = passwordEncoder().encode("123456");
//                return new User("fox", pw, AuthorityUtils.commaSeparatedStringToAuthorityList("admin.user"));
//            }
//        };
//    }
//
//    @Bean(name = "passwordEncoder2")
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
