package com.wwb.hystrixtest;

import com.wwb.hystrixtest.service.HelloCommand;
import com.wwb.hystrixtest.service.HelloCommand2;

public class T {

    private static void test1() throws Exception {
        HelloCommand command = new HelloCommand();
        String result = command.execute();
        System.out.println(result);
    }

    private static void test2() throws Exception {
        for (int i = 0; i < 30; i++) {
            HelloCommand2 command = new HelloCommand2();
            String result = command.execute();
            System.out.println("circuit Breaker is open : " + command.isCircuitBreakerOpen());
            if(command.isCircuitBreakerOpen()){
                Thread.currentThread().sleep(500);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        test2();
        //test1();
    }
}
