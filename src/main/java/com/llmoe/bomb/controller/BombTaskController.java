package com.llmoe.bomb.controller;


import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.TableDataInfo;
import com.llmoe.bomb.common.utils.UserAgentGetter;
import com.llmoe.bomb.entity.BombTask;
import com.llmoe.bomb.service.BombTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 轰炸请求 前端控制器
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Controller
@RequestMapping("/admin/bomb-task")
public class BombTaskController extends BaseController {
    /**
     * 轰炸请求
     */
    @Autowired
    private BombTaskService bombTaskService;

    /**
     * 轰炸请求管理页面
     *
     * @return 结果
     */
    @GetMapping()
    public String index() {
        return "admin/bomb-task";
    }


    /**
     * 轰炸请求列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(BombTask bombTask) {
        startPage();
        List<BombTask> list = bombTaskService.list(new QueryWrapper<BombTask>(bombTask).orderByDesc("create_time"));
        return getDataTable(list);
    }

    /**
     * 轰炸请求列表删除
     *
     * @return 结果
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultBean delete(Integer id) {
        return toResult(bombTaskService.removeById(id));
    }

    /**
     * 轰炸请求列表批量删除
     *
     * @return 结果
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    public ResultBean batchDelete(String ids) {
        return toResult(bombTaskService.batchDelete(ids));
    }

    /**
     * 轰炸请求添加更新
     *
     * @return 结果
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResultBean saveOrUpdate(BombTask bombTask, HttpServletRequest request) {
        //添加
        if (bombTask.getId() == null) {
            //查询号码正在排队的
            BombTask one = bombTaskService.getOne(new QueryWrapper<BombTask>().eq("phone", bombTask.getPhone()).eq("is_attack", 0));
            if (one != null) {
                return error("号码已在队列中");
            }
            bombTask.setCreateTime(new DateTime());
            bombTask.setIsAttack(0);

            UserAgentGetter getter = new UserAgentGetter(request);
            //IP地址
            bombTask.setRequestIp(getter.getIpAddr());
            //浏览器
            bombTask.setRequestBrowser(getter.getBrowser());
            //设备名称
            bombTask.setRequestDevice(getter.getDevice());
        }
        return toResult(bombTaskService.saveOrUpdate(bombTask));
    }
}

