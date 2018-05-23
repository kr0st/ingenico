package lv.javaguru.courses.ingenico.lecture4.streams.t40_filtering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Filtering {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("ab");
        strings.add("abc");
        strings.add("abcde");
        List<String> filtered = filterByLenghtWithStream(strings, 3);
        System.out.println(filtered);
    }

    static List<String> filterByLenghtWithStream(List<String> strings, int length) {
        return strings.stream()
                      .filter(string -> string.length() >= length)
                      .collect(Collectors.toList());
    }

    static List<String> filterByLength(List<String> strings, int length) {
        List<String> result = new ArrayList<>();
        for (String string : strings) {
            if (string.length() >= length) {
                result.add(string);
            }
        }
        return result;
    }

}
