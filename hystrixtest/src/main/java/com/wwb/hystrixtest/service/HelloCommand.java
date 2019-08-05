package com.wwb.hystrixtest.service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HelloCommand extends HystrixCommand<String> {

    public HelloCommand() {
        super(HystrixCommandGroupKey.Factory.asKey("test"));
    }

    @Override
    protected String run() throws Exception {
        //模拟请求外部接口需要的时间长度
        Thread.sleep(500);
        return "sucess";
    }

    @Override
    protected String getFallback() {
        //当外部请求超时后，会执行fallback里的业务逻辑
        System.out.println("执行了回退方法");
        return "error";
    }
}
