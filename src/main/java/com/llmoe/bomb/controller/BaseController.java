package com.llmoe.bomb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.common.TableDataInfo;
import com.llmoe.bomb.common.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 控制器基类
 *
 * @author llmoe
 */
public class BaseController {

    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }


    /**
     * 设置请求分页数据(Layui)
     */
    protected void startPage() {
        Integer pageNum = ServletUtils.getParameterToInt("page");
        Integer pageSize = ServletUtils.getParameterToInt("limit");

        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }


    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setData(list);
        rspData.setCount(new PageInfo(list).getTotal());
        return rspData;
    }


    /**
     * @param row 影响的行数
     */
    protected ResultBean toResult(int row) {
        return row > 0 ? ResultBean.success() : ResultBean.error();
    }

    /**
     * @param row 影响的行数
     */
    protected ResultBean toResult(boolean row) {
        return row ? ResultBean.success() : ResultBean.error();
    }


    /**
     * @param o 对象实体类
     */
    protected ResultBean toResult(Object o) {
        return o != null ? ResultBean.success() : ResultBean.error();
    }

    /**
     * 返回成功
     */
    public ResultBean success(Object date) {
        return ResultBean.success("操作成功", date);
    }

    /**
     * 返回成功
     */
    public ResultBean success() {
        return ResultBean.success("操作成功");
    }

    /**
     * 返回失败消息
     */
    public ResultBean error(Object date) {
        return ResultBean.error("操作失败", date);
    }

    /**
     * 返回成功
     */
    public ResultBean success(String msg) {
        return ResultBean.success(msg);
    }

    /**
     * 返回失败消息
     */
    public ResultBean error(String msg) {
        return ResultBean.error(msg);
    }


    /**
     * redirect跳转
     *
     * @param url 目标url
     */
    protected String redirect(String url) {
        return "redirect:" + url;
    }


}
