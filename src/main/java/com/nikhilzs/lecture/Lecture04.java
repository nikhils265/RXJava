package com.nikhilzs.lecture;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Objects;

public class Lecture04 {
    public static void main(String[] args) {
        Single<String> single = createSingle();
        single.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
        Maybe<String> maybe = createMaybe();
        maybe.subscribe(new MaybeObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static Maybe<String> createMaybe() {
        return Maybe.create(emitter -> {
            String someContent = "Some Content fetched from DB";
            if (Objects.nonNull(someContent)) {
                emitter.onSuccess(someContent);
            } else {
                emitter.onComplete();
            }
        });
    }

    private static Single<String> createSingle() {
        return Single.create(emitter -> {
            String user = "Nikhil";
            if (Objects.nonNull(user)) {
                emitter.onSuccess(user);
            } else {
                emitter.onError(new Exception("User not found"));
            }
        });
    }
}
