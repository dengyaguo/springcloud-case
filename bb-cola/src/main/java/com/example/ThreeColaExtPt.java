package com.example;

import com.example.cola.extension.Extension;
import org.springframework.stereotype.Component;

@Extension(bizId = "tenant-1", useCase = "userCase-1",scenario = "scenario-1")
@Component
public class ThreeColaExtPt extends AbstractColaExtPt {
    @Override
    protected void afterMethod() {
        super.afterMethod();
    }

    @Override
    protected String doPlace() {
        return "ThreeColaExtPt";
    }

    @Override
    protected void beforeMethod() {
        super.beforeMethod();
    }

}
