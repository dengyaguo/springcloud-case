package com.example.cloud;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.cloud.sentinel.SentinelComand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MyCloudApplication {
    @Autowired
    private SentinelComand sentinelComand;


    public static void main(String[] args) {
        SpringApplication.run(MyCloudApplication.class, args);
    }


//    @RequestMapping("/getValue")
//    @SentinelResource(
//            value = "/getValue",
//            blockHandler = "doBlock",//限流
//            fallback = "doFallBack")//降级
//    public String getValue() {
//        return config + configCommon;
//    }

    @RequestMapping("/test")
    public String test() {
        return sentinelComand.test();
    }


    public String doBlock(BlockException e) {
        return "doBlock";
    }

    public String doFallBack() {
        return "doFallBack";
    }
}
