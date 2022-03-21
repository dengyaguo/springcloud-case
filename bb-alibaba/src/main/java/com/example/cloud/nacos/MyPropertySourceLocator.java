package com.example.cloud.nacos;//package com.example.myeureka.nacos;
//
//import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
//import org.springframework.core.env.Environment;
//import org.springframework.core.env.PropertySource;
//
//import java.util.Map;
//
//public class MyPropertySourceLocator implements PropertySourceLocator {
//
//
//    /**
//     *  1.PropertySourceBootstrapConfiguration
//     *
//     *  2.NacosPropertySourceLocator
//     *
//     *  3.NacosConfigService
//     *
//     * @return
//     */
//    @Override
//    public PropertySource<?> locate(Environment environment) {
//        MyPropertySource myPropertySource = new MyPropertySource("xxx", loadFile());
//        return myPropertySource;
//    }
//
//    private Map<String, Object> loadFile() {
//        //读取classPath下文件
//        return null;
//    }
//
//
//}
