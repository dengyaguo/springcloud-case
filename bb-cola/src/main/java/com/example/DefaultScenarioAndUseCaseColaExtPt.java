package com.example;

import com.example.cola.extension.Extension;
import org.springframework.stereotype.Component;

@Extension(bizId = "tenant-1")
@Component
public class DefaultScenarioAndUseCaseColaExtPt extends AbstractColaExtPt {

    @Override
    protected void afterMethod() {
        super.afterMethod();
    }

    @Override
    protected String doPlace() {
        return "DefaultScenarioAndUseCase_placeExtPt";
    }

    @Override
    protected void beforeMethod() {
        super.beforeMethod();
    }
}
