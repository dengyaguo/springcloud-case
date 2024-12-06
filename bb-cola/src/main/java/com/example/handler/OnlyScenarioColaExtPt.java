package com.example.handler;

import com.example.AbstractColaExtPt;
import com.example.cola.extension.Extension;
import org.springframework.stereotype.Component;

@Extension(scenario = "scenario-1")
@Component
public class OnlyScenarioColaExtPt extends AbstractColaExtPt {
    @Override
    protected void afterMethod() {
        super.afterMethod();
    }

    @Override
    protected String doPlace() {
        return "OnlyScenarioColaExtPt";
    }

    @Override
    protected void beforeMethod() {
        super.beforeMethod();
    }

}
