package com.example;

import com.example.cola.extension.Extension;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;


//@ConditionalOnProperty
@Extension
@Component
public class DefaultColaExtPt extends AbstractColaExtPt {


    @Override
    protected void afterMethod() {
        super.afterMethod();
    }

    @Override
    protected String doPlace() {
        return "Default_placeExtPt";
    }

    @Override
    protected void beforeMethod() {
        super.beforeMethod();
    }
}
