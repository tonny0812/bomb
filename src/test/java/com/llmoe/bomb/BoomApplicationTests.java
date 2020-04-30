package com.llmoe.bomb;

import org.junit.jupiter.api.Test;

class BoomApplicationTests {

    @Test
    void contextLoads() {
        int survive = 0;
//        for (int i = 0; i < 100; i++) {
//            try {
//                Thread.sleep(5000);
//                long old = System.currentTimeMillis();
//
//                String ip = HttpUtil.get("http://www.xiladaili.com/api/?uuid=39fcb13870994c1f99a634d0b3d5e964&num=1&place=%E4%B8%AD%E5%9B%BD&protocol=1&sortby=2&repeat=1&format=3&position=1");
//                System.out.println("ip = " + ip);
//                String[] split = StrUtil.splitToArray(ip, ':');
//
//                if (split.length != 2) {
//                    System.out.println("获取不到IP");
//                    continue;
//                }
//                System.out.println("IP = " + split[0]);
//                System.out.println("端口 = " + split[1]);
//
//                HttpResponse res = HttpRequest.get("https://www.baidu.com/")
//                        .header(Header.USER_AGENT, "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16B92 Html5Plus/1.0")//头信息，多个头信息多次调用此方法即可
//                        .timeout(5000)//超时，毫秒
//                        .setProxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(split[0], Convert.toInt(split[1]))))
//                        .execute();
//
//                long now = System.currentTimeMillis();
//                survive++;
//                Console.log("响应时间：" + ((now - old) / 1000.0) + " s");
//                Console.log("响应状态：" + res.getStatus());
//
//            } catch (Exception e) {
//                Console.error(e.getMessage());
//            }
//        }
        System.out.println("存活 = " + survive);
    }

}
