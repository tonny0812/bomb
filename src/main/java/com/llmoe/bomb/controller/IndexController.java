package com.llmoe.bomb.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.entity.ApiInterface;
import com.llmoe.bomb.entity.BombTask;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.entity.Whitelist;
import com.llmoe.bomb.service.ApiInterfaceService;
import com.llmoe.bomb.service.BombTaskService;
import com.llmoe.bomb.service.ConfigService;
import com.llmoe.bomb.service.WhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 首页
 *
 * @author llmoe
 * @date 2020/4/4 22:20
 */
@Controller
public class IndexController extends BaseController {

    /**
     * 网站设置
     */
    @Autowired
    private ConfigService configService;

    /**
     * 白名单
     */
    @Autowired
    private WhitelistService whitelistService;

    /**
     * 轰炸队列
     */
    @Autowired
    private BombTaskService bombTaskService;

    /**
     * API
     */
    @Autowired
    private ApiInterfaceService apiInterfaceService;


    /**
     * 进入前台首页页面
     *
     * @return 页面
     */
    @GetMapping(value = {"/", "index"})
    public String index(ModelMap map, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Config> list = configService.list();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(Config::getKey, Config::getValue));
        //网站设置
        map.addAttribute("config", collect);
        //白名单数量
        map.addAttribute("whitelist", whitelistService.count());
        //API数量
        map.addAttribute("apiCount", apiInterfaceService.count(new QueryWrapper<ApiInterface>().eq("status", 1)));
        //轰炸总数量
        map.addAttribute("bombAll", bombTaskService.count());
        //今日轰炸数量
        map.addAttribute("bombNow", bombTaskService.count(new QueryWrapper<BombTask>().ge("create_time", DateUtil.formatDate(new DateTime()))));

        PageHelper.startPage(pageNum, 10);
        //轰炸记录
        List<BombTask> bombTasks = bombTaskService.listCoverUp();
        PageInfo<BombTask> pageInfo = new PageInfo<BombTask>(bombTasks);
        map.addAttribute("bombList", pageInfo);
        return "index";
    }


    /**
     * 白名单添加
     *
     * @return 结果
     */
    @PostMapping("/saveWhitelist")
    @ResponseBody
    public ResultBean saveWhitelist(@Validated Whitelist whitelist) {
        Whitelist one = whitelistService.getOne(new QueryWrapper<Whitelist>().eq("phone", whitelist.getPhone()));
        if (one != null) {
            return error("号码已录入白名单");
        }
        whitelist.setCreateTime(new DateTime());
        return toResult(whitelistService.save(whitelist));
    }

    /**
     * 前台请求轰炸
     *
     * @return 结果
     */
    @PostMapping("/saveBomb")
    @ResponseBody
    public ResultBean saveBomb(@Validated BombTask bombTask, HttpServletRequest request) {
        return toResult(bombTaskService.startBombing(bombTask, request));
    }
}
