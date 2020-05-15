package com.llmoe.bomb.controller;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.TableDataInfo;
import com.llmoe.bomb.entity.Whitelist;
import com.llmoe.bomb.service.WhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 白名单 前端控制器
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Controller
@RequestMapping("/admin/whitelist")
public class WhitelistController extends BaseController {

    @Autowired
    private WhitelistService whitelistService;

    /**
     * 白名单界面
     *
     * @return 界面
     */
    @GetMapping()
    public String index() {
        return "admin/whitelist";
    }


    /**
     * 白名单列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Whitelist whitelist) {
        startPage();
        List<Whitelist> list = whitelistService.list(new QueryWrapper<Whitelist>().like(StrUtil.isNotBlank(whitelist.getPhone()), "phone", whitelist.getPhone()).orderByDesc("create_time"));
        return getDataTable(list);
    }

    /**
     * 白名单列表删除
     *
     * @return 结果
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultBean delete(Integer id) {
        return toResult(whitelistService.removeById(id));
    }

    /**
     * 白名单列表批量删除
     *
     * @return 结果
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    public ResultBean batchDelete(String ids) {
        return toResult(whitelistService.batchDelete(ids));
    }

    /**
     * 白名单添加更新
     *
     * @return 结果
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResultBean saveOrUpdate(Whitelist whitelist) {
        Whitelist one = whitelistService.getOne(new QueryWrapper<Whitelist>().eq("phone", whitelist.getPhone()));
        //新增
        if (whitelist.getId() == null) {
            if (one != null) {
                return error("号码录入白名单");
            }
            whitelist.setCreateTime(new DateTime());
        }
        //更新
        if (one != null) {
            if (!one.getId().equals(whitelist.getId())) {
                return error("号码录入白名单");
            }
        }
        return toResult(whitelistService.saveOrUpdate(whitelist));
    }

}

