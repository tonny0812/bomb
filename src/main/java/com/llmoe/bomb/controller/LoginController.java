package com.llmoe.bomb.controller;

import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 登录控制器
 *
 * @author llmoe
 * @date 2020/4/4 21:22
 */
@Controller
public class LoginController {

    /**
     * 网站设置
     */
    @Autowired
    private ConfigService configService;

    /**
     * @return 登录页面
     */
    @GetMapping("/login")
    public String login(ModelMap map) {
        List<Config> list = configService.list();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(Config::getKey, Config::getValue));
        //网站设置
        map.addAttribute("config", collect);
        return "login";
    }
}
