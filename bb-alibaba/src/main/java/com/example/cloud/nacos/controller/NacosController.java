package com.example.cloud.nacos.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RefreshScope
@RestController
@RequestMapping("/nacos")
public class NacosController {

    @Autowired
    private Environment environment;

    @Value("${nacos.config:default}")
    private String config;

    @Value("${nacos.config.ext:default}")
    private String configExt;

    @Value("${nacos.config.common:default}")
    private String configCommon;

    @RequestMapping("/getValue")
    public String getValue() {
        log.info("environment:" + JSON.toJSONString(environment));
        return config + "|" + configExt+ "|" + configCommon;
    }
}
