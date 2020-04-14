/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author rasmu
 */
public class StreamsExample2 {

    public static void main(String[] args) {
        String[] number = {"10", "11", "12", "13", "14", "15"};
        boolean even = Arrays.stream(number).mapToInt(x -> Integer.parseInt(x)).anyMatch(x -> x % 2 == 1);
        System.out.println(even);

        List<Integer> intList = Arrays.asList(1995, 1997, 2008, 1966, 1997, 2001, 2020, 1980, 1999);
        Map<Object, List<Integer>> result = intList.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(3)
                .sorted()
                .collect(Collectors.groupingBy(e -> Math.floor(Math.floor(e - (Math.floor(e / 100) * 100)) / 10) * 10));
        System.out.println(result);
    }
}
