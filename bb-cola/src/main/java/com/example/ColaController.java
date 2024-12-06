package com.example;

import com.alibaba.fastjson.JSONObject;
import com.example.cola.extension.BizScenario;
import com.example.handler.ExtensionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/extension")
public class ColaController {

    @Resource
    private ExtensionExecutor extensionExecutor;

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws IOException {

        placeOrder(BizScenario.valueOf("tenant-1", "userCase-1","scenario-1"));
        placeOrder(BizScenario.valueOf("tenant-2", "userCase-1","scenario-1"));

        placeOrder(BizScenario.valueOf("tenant-2", "userCase-1","scenario-3"));
        placeOrder(BizScenario.valueOf("tenant-3","userCase-2","scenario-2"));


        placeOrder(BizScenario.newDefault());




//        auditOrder(BizScenario.newDefault());
//        execute:"DefaultScenarioColaExtPt"
//        execute:"DefaultScenarioAndUseCaseColaExtPt"
//        execute:"DefaultColaExtPt"
//        execute:"DefaultScenarioAndUseCaseColaExtPt"

    }

    private void placeOrder(BizScenario bizScenario) {
        final Object execute = extensionExecutor.execute(ColaExtPt.class, bizScenario, extension -> extension.placeOrder(null));
        log.info("placeOrder:{}", JSONObject.toJSONString(execute));
    }

//    private void auditOrder(BizScenario bizScenario) {
//        final Object execute = extensionExecutor.execute(ColaExtPt.class, bizScenario, extension -> extension.auditOrder(null));
//        log.info("auditOrder:{}", JSONObject.toJSONString(execute));
//    }
}
