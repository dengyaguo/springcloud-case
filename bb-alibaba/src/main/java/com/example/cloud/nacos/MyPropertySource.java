package com.example.cloud.nacos;

import org.springframework.core.env.MapPropertySource;
import java.util.Map;

public class MyPropertySource extends MapPropertySource {
    public MyPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }
}
