package com.wwb.rxjava2test;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import java.util.concurrent.TimeUnit;

public class T {
    private static void test1(){
        Observable.just("test","testx").subscribe(System.out::println);
    }

    private static void test2(){
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("test1");
            emitter.onNext("test2");
            emitter.onComplete();
        }).subscribe(System.out::println);
    }

    private static void test3(){
        Observable.fromArray("a","b","c","d","e","f","g","h","i","j","k","l","m","n").subscribe(System.out::println);
        System.out.println("z");
    }

    private static void test4(){
        //惰性求值，subscribe才创建 Observable
        Observable.defer(()->{
            Thread.sleep(30000);
            return Observable.just("a","b","c");
        }).subscribe(System.out::println);
        System.out.println("z");
    }

    private static void test5() throws Exception {
        Observable.interval(1, TimeUnit.SECONDS).take(10).subscribe(System.out::println);
        System.out.println("z");
        Thread.sleep(11000);
    }

    public static void main(String[] args) throws Exception {
        test5();
        //test4();
        //test3();
        //test2();
        //test1();
    }
}
