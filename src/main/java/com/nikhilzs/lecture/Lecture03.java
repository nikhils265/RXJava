package com.nikhilzs.lecture;

import io.reactivex.rxjava3.core.Observable;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Lecture03 {
    public static void main(String[] args) {
        Observable<Long> intervalObservable = Observable.interval(1, TimeUnit.SECONDS);
        intervalObservable.subscribe(item -> {
            System.out.println(item);
        });

        new Scanner(System.in).nextLine();
    }
}
