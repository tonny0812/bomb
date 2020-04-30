package com.llmoe.bomb.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author : llmoe
 * @date : 2020/04/04 20:27
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    LoginFailureHandlerConfig loginFailureHandlerConfig;

    @Autowired
    LoginSuccessHandlerConfig loginSuccessHandlerConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    /**
     * addFilterBefore(): 自定义添加一个过滤器，并且放在Spring Security某个过滤器的前面
     * fromLogin(): 表单认证
     * httpBasic(): 弹出框认证
     * loginPage(): 登录页面地址（因为SpringBoot无法直接访问页面，所以这通常是一个路由地址）
     * loginProcessingUrl(): 登录表单提交地址
     * successHandler(): 自定义身份校验成功成功处理器
     * failureHandler(): 自定义身份校验失败失败处理器
     * authorizeRequests() 身份认证请求
     * anyRequest(): 所有请求
     * authenticated(): 身份认证
     * .csrf().disable(): 关闭Spring Security的跨站请求伪造的功能
     */

    /**
     * 静态资源设置
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        //不拦截静态资源,所有用户均可访问的资源
        webSecurity.ignoring().antMatchers("/assets/**", "/component/**", "/favicon.ico");
    }

    /**
     * 密码认证
     *
     * @throws Exception 异常
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用自定义认证规则，并且使用BCrypt算法处理密码
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/", "/index", "/saveWhitelist", "/saveBomb")
                .permitAll().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error")
                .defaultSuccessUrl("/admin/index")
                .usernameParameter("username")
                .passwordParameter("password")
                // 自定义登录失败处理
                .failureHandler(loginFailureHandlerConfig)
                // 自定义登录成功处理
                .successHandler(loginSuccessHandlerConfig)

                //退出登录地址
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/")

                .and()
                //设置跨域防护 放行连接
                .csrf().ignoringAntMatchers("/admin/**", "/login", "/logout");

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember-me").tokenValiditySeconds(2 * 24 * 60 * 60);
        http.headers().frameOptions().disable();
    }
}
