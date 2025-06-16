package com.nikhilzs;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaConcurrency {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
//        System.out.println(squareJavaConcurrency(list));
        System.out.println(squareRx(list));

    }

    private static List<Integer> squareJavaConcurrency(List<Integer> list) throws InterruptedException {
        List<Callable<Integer>> callables = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(Integer value : list) {
            Callable<Integer> task = () -> {
                System.out.println(
                        String.format("Processing the item %d on thread %s", value,
                                Thread.currentThread().getName()));
                Thread.sleep(5000);
                return value*value;
            };
            callables.add(task);
        }
        executorService.invokeAll(callables).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }).forEach(result::add);
        executorService.close();
        return result;
    }

    private static List<Integer> squareRx(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        Observable.fromIterable(list)
                .flatMap(n ->
                        Observable.just(n)
                                .map(number -> {
                                    System.out.println(
                                            String.format("Processing the item %d on thread %s", number,
                                                    Thread.currentThread().getName()));
                                    Thread.sleep(5000);
                                    return number*number;
                                }).subscribeOn(Schedulers.io()))
                .blockingSubscribe(result::add);
        return result;
    }
}
