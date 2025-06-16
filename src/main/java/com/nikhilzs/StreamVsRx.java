package com.nikhilzs;

import io.reactivex.rxjava3.core.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamVsRx {
    public static void main(String[] args) {
        List<String> inputList = Arrays.asList("1", "2", "3", "4");
        List<Integer> result = processWithStream(inputList);
        System.out.println(result);
        result = processWithRX(inputList);
        System.out.println(result);

    }

    private static List<Integer> processWithRX(List<String> inputList) {
        List<Integer> result = new ArrayList<>();
        Observable.fromIterable(inputList)
                .map(Integer::parseInt)
                .map(n->n*n)
                .filter(n->n%2==0)
                .blockingSubscribe(result::add);
        return result;
    }

    private static List<Integer> processWithStream(List<String> inputList) {
        return inputList.stream()
                .map(Integer::parseInt)
                .map(n ->n*n)
                .filter(n -> n%2==0)
                .collect(Collectors.toList());
    }


}