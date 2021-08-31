package com.liquor.springsecuritydemo.service;

import com.liquor.springsecuritydemo.mapper.PermissionMapper;
import com.liquor.springsecuritydemo.mapper.UserMapper;
import com.liquor.springsecuritydemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: Liquor.Huang
 * @Date 2021/8/27 17:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        String hashpw = BCrypt.hashpw("123456",BCrypt.gensalt());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("liquor").password("{noop}123456").authorities("admin").build();
        return userDetails;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //从mysql查询用户
//        User user = getByUsername(username);
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if (user != null) {
//            List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
//            //设置权限
//            permissions.forEach(permission -> {
//                if (permission != null && !StringUtils.isEmpty(permission.getEnname())) {
//                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
//                    authorities.add(grantedAuthority);
//                }
//            });
//            //封装成UserDetails的实现类
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//        } else {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//    }
}
