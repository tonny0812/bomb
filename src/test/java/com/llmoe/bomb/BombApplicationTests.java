package com.llmoe.bomb;

import org.junit.jupiter.api.Test;

//@SpringBootTest
class BombApplicationTests {


    @Test
    void contextLoads() throws InterruptedException {
       /* List<MsgApi> list = msgApiMapper.selectList(new QueryWrapper<MsgApi>().orderByDesc("id"));
        ExecutorService executorService = ThreadUtil.newExecutor(300);

        for (MsgApi msgApi : list) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(StrUtil.replace(msgApi.getUrl(), "[手机号码]", "18689249984"));
                        buffer.append("?");
                        buffer.append(StrUtil.replace(msgApi.getPost(), "[手机号码]", "18689249984"));
                        System.out.println("api = " + buffer.toString());
                        //请求接口 替换用户手机号
                        HttpResponse response = HttpRequest.post(buffer.toString()).timeout(3000).execute();
                        //状态NO 200 删除
                        if (response.getStatus() != 200) {
                            msgApiMapper.deleteById(msgApi.getId());
                        }
                    } catch (Exception e) {
                        msgApiMapper.deleteById(msgApi.getId());
                        Console.error(e.getMessage());
                    }
                }
            };
            System.out.println(((ThreadPoolExecutor) executorService).getActiveCount());

            if (((ThreadPoolExecutor) executorService).getActiveCount() > 290) {
                //休眠等线程自动关闭
                Thread.sleep(10000);
            }
            executorService.execute(runnable);

        }*/
    }

    @Test
    public void demo2() {
      /*  List<MsgApi> list = msgApiMapper.selectList(new QueryWrapper<MsgApi>().orderByDesc("id"));

        for (MsgApi msgApi : list) {
            try {
                StringBuffer buffer = new StringBuffer();
                buffer.append(StrUtil.replace(msgApi.getUrl(), "[手机号码]", "18689249984"));
                buffer.append("?");
                buffer.append(StrUtil.replace(msgApi.getPost(), "[手机号码]", "18689249984"));
                System.out.println("api = " + buffer.toString());
                //请求接口 替换用户手机号
                HttpResponse response = HttpRequest.post(buffer.toString()).timeout(3000).execute();
                //状态NO 200 删除
                if (response.getStatus() != 200) {
                    msgApiMapper.deleteById(msgApi.getId());
                }
                Console.log(response.body());
            } catch (Exception e) {
                msgApiMapper.deleteById(msgApi.getId());
                Console.error(e.getMessage());
            }
        }*/
    }

}
