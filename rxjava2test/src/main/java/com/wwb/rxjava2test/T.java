package com.wwb.rxjava2test;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

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

    private static void test6() throws Exception {
        Observable.interval(1, TimeUnit.SECONDS).take(10).blockingSubscribe(System.out::println);
        System.out.println("z");
    }

    private static void test7(){
        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);
    }

    private static void test8(){
        Flowable.range(1, 10).flatMap(v ->
                Flowable.just(v)
                        .subscribeOn(Schedulers.computation())
                        .map(w -> w * w)
        ).blockingSubscribe(System.out::println);
    }

    private static void test9(){
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(v -> v * v)
                .sequential()
                .blockingSubscribe(System.out::println);
    }

    private static void test10(){
        Flowable.range(0,9).flatMap(it->Flowable.range(1,9).map(j->it*10 + j))
                .blockingSubscribe(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        test10();
        //test9();
        //test8();
        //test7();
        //test6();
        //test5();
        //test4();
        //test3();
        //test2();
        //test1();
    }
}
