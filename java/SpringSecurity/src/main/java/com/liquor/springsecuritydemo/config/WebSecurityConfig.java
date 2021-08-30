package com.liquor.springsecuritydemo.config;

import com.liquor.springsecuritydemo.service.UserService;
import com.liquor.springsecuritydemo.strategy.MyExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 15:54
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//表单提交
                .loginPage("/login.html")//自定义登录页面
                .loginProcessingUrl("/user/login")//登录访问路径，必须和表单提交接口一样
                .successForwardUrl("/main")//认证成功之后转发的路径,必须是Post请求
                .failureForwardUrl("/toerror")//认证失败之后转发的路径,必须是Post请求
                .and().authorizeRequests()
                //设置哪些路径可以直接访问，不需要认证
                .antMatchers("/user/login", "/login.html", "/error.html").permitAll()
                .anyRequest().authenticated()//需要认证
                .and().csrf().disable();//关闭csrf防护
//                .sessionManagement()
//                .invalidSessionUrl("/session/invalid")
//                .maximumSessions(1)
//                .expiredSessionStrategy(new MyExpiredSessionStrategy())
//                .maxSessionsPreventsLogin(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
