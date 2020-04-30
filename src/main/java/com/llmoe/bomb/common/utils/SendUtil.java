package com.llmoe.bomb.common.utils;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.llmoe.bomb.common.Constant;
import com.llmoe.bomb.common.ResultBean;
import com.llmoe.bomb.entity.ApiInterface;

import java.util.Map;

/**
 * 短信发送工具
 *
 * @author llmoe
 * @date 2020/4/29 13:59
 */
public class SendUtil {
    /**
     * 发送短信
     *
     * @param api   APi 接口
     * @param phone 手机号码
     */
    public static ResultBean send(ApiInterface api, String phone) {
        long old = System.currentTimeMillis();

        //请求接口 替换用户手机号
        api.setUrl(StrUtil.replace(api.getUrl(), "target_Phone", phone));

        Console.log("请求接口：" + api.getUrl());

        //请求参数 替换用户手机号
        api.setParm(StrUtil.replace(api.getParm(), "target_Phone", phone));

        Console.log("请求参数：" + api.getParm());

        //构建HTTP请求
        HttpRequest httpRequest = null;


        //0 GET 1 POST
        if (Constant.HTTP_GET.equals(api.getType())) {
            httpRequest = HttpRequest.get(api.getUrl());
        } else {
            httpRequest = HttpRequest.post(api.getUrl());
        }

        //如果头不为空 并且为JSON字符串
        if (StrUtil.isNotBlank(api.getHeaders()) && JSONUtil.isJson(api.getHeaders())) {
            Map map = JSONUtil.parseObj(api.getHeaders());
            httpRequest.addHeaders(map);
        } else {
            httpRequest.header(Header.USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16B92 Html5Plus/1.0");
            httpRequest.header(Header.ACCEPT_ENCODING, "gzip, deflate, br");
            httpRequest.header(Header.ACCEPT_LANGUAGE, "zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2");
            httpRequest.header(Header.CACHE_CONTROL, "max-age=0");
        }

        //如果Cookie不为空
        if (StrUtil.isNotBlank(api.getCookie())) {
            httpRequest.cookie(api.getCookie());
        }

        //如果请求参数不为空
        if (StrUtil.isNotBlank(api.getParm())) {
            httpRequest.body(api.getParm());
        }

        HttpResponse response = httpRequest.timeout(Constant.HTTP_TIME_OUT).execute();

        long now = System.currentTimeMillis();
        Console.log("响应时间：" + ((now - old) / 1000.0) + " s");
        Console.log("响应内容：" + response.body());
        return ResultBean.success(response.body());
    }


}
