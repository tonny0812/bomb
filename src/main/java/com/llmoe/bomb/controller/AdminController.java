package com.llmoe.bomb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台首页
 *
 * @author llmoe
 * @date 2020/4/5 16:50
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    /**
     * 网站设置
     */
    @Autowired
    private ConfigService configService;

    /**
     * 进入后台首页页面
     *
     * @return 页面
     */
    @GetMapping("/index")
    public String adminIndex(ModelMap map) {
        List<Config> list = configService.list();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(Config::getKey, Config::getValue));
        map.addAttribute("config", collect);
        return "admin/index";
    }

    /**
     * 后台主页
     *
     * @return 界面
     */
    @GetMapping("/main")
    public String main() {
        return "admin/main";
    }

    /**
     * 重置密码
     *
     * @param oldPsw 旧的密码
     * @param newPsw 新密码
     * @return 结果
     */
    @PostMapping("/rePwd")
    @ResponseBody
    public ResultBean rePwd(String oldPsw, String newPsw) {
        Config password = configService.getOne(new QueryWrapper<Config>().eq("`key`", "password"));

        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        //判断加密后密码一致性
        boolean f = bcryptPasswordEncoder.matches(oldPsw, password.getValue());
        if (f) {
            String hashPass = bcryptPasswordEncoder.encode(newPsw);
            Map<String, String> map = new HashMap<>();
            map.put("password", hashPass);

            return toResult(configService.updateConfig(map));
        }
        return error("旧密码错误");
    }

}
