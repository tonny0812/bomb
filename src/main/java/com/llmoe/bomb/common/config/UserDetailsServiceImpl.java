package com.llmoe.bomb.common.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.Constant;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author : llmoe
 * @date : 2020/04/09 12:27
 */
@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ConfigService configService;

    /**
     * 自定义用户密码验证
     * @param username 用户名
     * @return 结果
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        Config user = configService.getOne(new QueryWrapper<Config>().eq("`key`", Constant.USERNAME).eq("value", username));

        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //获取密码
        Config pwd = configService.getOne(new QueryWrapper<Config>().eq("`key`", Constant.PASSWORD));


        // 添加权限
        //  authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(user.getValue(), pwd.getValue(), authorities);
    }
}
