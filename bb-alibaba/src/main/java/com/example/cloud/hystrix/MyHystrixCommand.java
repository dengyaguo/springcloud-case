package com.example.cloud.hystrix;

import com.netflix.hystrix.HystrixCommand;

import java.util.concurrent.Future;


public class MyHystrixCommand extends HystrixCommand<String> {
    protected MyHystrixCommand(Setter setter) {
        super(setter);
    }

    @Override
    protected String run() throws Exception {
        return null;
    }

    public static void main(String[] args) {
        MyHystrixCommand myHystrixCommand = new MyHystrixCommand(null);
        String execute = myHystrixCommand.execute();//同步调用

        Future<String> queue = myHystrixCommand.queue();//异步调用
    }
}
