package com.nikhilzs.lecture;

import io.reactivex.rxjava3.core.Observable;

public class Lecture01 {
    public static void main(String[] args) {
        Observable<String> observable = null;

        /**
         * 1. Observable.just()
         */
        System.out.println("Observable.just()");
        observable = Observable.just("Nikhil", "Sharma");
        observable.blockingForEach(System.out::println);

        /**
         * 2. Observable.fromArray()/fromIterable()
         */
        System.out.println("Observable.fromArray()/fromIterable()");
        observable = Observable.fromArray("Nikhil", "Sharma");
        observable.blockingForEach(System.out::println);

        /**
         * 3. Observable.create()
         */
        System.out.println("Observable.create()");
        observable = Observable.create(emitter -> {
            emitter.onNext("Nikhil");
            emitter.onNext("Sharma");
            emitter.onComplete();
        });
        observable.blockingForEach(System.out::println);

        /**
         * 4. Observable.defer()
         * Defers the creation until an observer subscribes.
         * Good for lazy evaluation.
         */
        System.out.println("Observable.defer()");
        observable = Observable.defer(() -> Observable.just("Nikhil"));
        observable.subscribe(item -> {
            System.out.println(item); //onNext
        }, throwable -> {
            System.out.println(throwable.getMessage()); //onError
        }, () ->{
            System.out.println("Emission completed"); // onComplete
        });
    }
}
