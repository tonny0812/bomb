package com.llmoe.bomb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 启动入口哦
 */
@EnableAsync //开启异步注解功能
@EnableCaching  //开启SpringBoot缓存
@EnableWebSecurity //开启跨域保护
@MapperScan("com.llmoe.bomb.mapper") //扫描包
@SpringBootApplication
public class BombApplication {
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BombApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application 工程启动成功 is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "\n" +
                "----------------------------------------------------------\n");
    }
}
