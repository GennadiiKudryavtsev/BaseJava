package com.urise.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] array = new int[9];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 9) + 1;
        }

        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(5);

        System.out.println(minValue(array));
        System.out.println(oddOrEven(list));//
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (accumulate, element) -> accumulate * 10 + element);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(Integer::sum).get();
        return integers.stream()
                .filter(num -> num % 2 == sum % 2)
                .collect(Collectors.toList());
    }
}
