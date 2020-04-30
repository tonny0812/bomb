package com.llmoe.bomb.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.TableDataInfo;
import com.llmoe.bomb.entity.ApiInterface;
import com.llmoe.bomb.service.ApiInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 短信API接口 前端控制器
 * </p>
 *
 * @author LLmoe
 * @since 2020-04-04
 */
@Controller
@RequestMapping("/admin/api-interface")
public class ApiInterfaceController extends BaseController {

    /**
     * APi 接口服务
     */
    @Autowired
    private ApiInterfaceService apiInterfaceService;

    /**
     * API接口管理页面
     *
     * @return 结果
     */
    @GetMapping()
    public String index() {
        return "admin/api-interface";
    }


    /**
     * API接口列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(ApiInterface apiInterface) {
        startPage();
        List<ApiInterface> list = apiInterfaceService.list(new QueryWrapper<ApiInterface>(apiInterface));
        return getDataTable(list);
    }

    /**
     * API接口列表删除
     *
     * @return 结果
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResultBean delete(Integer id) {
        return toResult(apiInterfaceService.removeById(id));
    }

    /**
     * API接口列表批量删除
     *
     * @return 结果
     */
    @PostMapping("/batchDelete")
    @ResponseBody
    public ResultBean batchDelete(String ids) {
        return toResult(apiInterfaceService.batchDelete(ids));
    }

    /**
     * API接口添加更新
     *
     * @return 结果
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public ResultBean saveOrUpdate(ApiInterface apiInterface) {
        try {
            //验证并且格式化JSON
            if (StrUtil.isNotBlank(apiInterface.getParm())) {
                //请求字符
                JSONObject parm = JSONUtil.parseObj(apiInterface.getParm());
                apiInterface.setParm(parm.toString());
            }
            if (StrUtil.isNotBlank(apiInterface.getHeaders())) {
                //请求头
                JSONObject head = JSONUtil.parseObj(apiInterface.getHeaders());
                apiInterface.setHeaders(head.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error("格式化JSON字符串出错");
        }
        return toResult(apiInterfaceService.saveOrUpdate(apiInterface));
    }


    /**
     * API接口运行测试
     *
     * @param id 接口ID
     * @return 结果
     */
    @PostMapping("/run")
    @ResponseBody
    public ResultBean run(Integer id) {
        return apiInterfaceService.run(id);
    }

}

