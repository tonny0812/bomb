package com.llmoe.bomb.controller;


import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 网站配置 前端控制器
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Controller
@RequestMapping("/admin/config")
public class ConfigController extends BaseController {
    /**
     * 网站设置
     */
    @Autowired
    private ConfigService configService;

    /**
     * 网站设置
     *
     * @return 页面
     */
    @GetMapping()
    public String index(ModelMap map) {
        List<Config> list = configService.list();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(Config::getKey, Config::getValue));
        map.addAttribute("config", collect);
        return "admin/website";
    }

    /**
     * 更新网站配置
     *
     * @return 结果
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultBean update(@RequestBody Map<String, String> options) {
        return toResult(configService.updateConfig(options));
    }
}

