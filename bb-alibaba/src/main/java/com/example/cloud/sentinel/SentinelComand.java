package com.example.cloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelComand {
    @SentinelResource(
            value = "doTest",
            blockHandler = "doBlock",//限流
            fallback = "doFallBack")//降级
    public String test() {
        return "test";
    }

    public String doBlock(BlockException e) {
        return "doBlock";
    }

    public String doFallBack() {
        return "doFallBack";
    }
}
