package com.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractColaExtPt implements ColaExtPt {


//    @Override
//    public final String auditOrder(Object o) {
//        beforeMethod();
//        final String s = doAudit();
//        afterMethod();
//        return s;
//    }


    @Override
    public final String placeOrder(Object o) {
        beforeMethod();
        final String s = doPlace();
        afterMethod();
        return s;
    }

    protected void afterMethod() {

    }


    protected String doPlace() {
        return "Abstract_doPlace";
    }


    protected void beforeMethod() {

    }


    protected String doAudit() {
        return "Abstract_doAudit";
    }
}
