package lv.javaguru.courses.ingenico.lecture4.streams.t41_mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("ab");
        strings.add("abc");
        strings.add("abcde");
        List<Integer> converted = convertToLengthWithStream(strings);
        System.out.println(converted);
    }

    static List<Integer> convertToLengthWithStream(List<String> strings){
        return strings.stream()
               .map(String::length)
               .collect(Collectors.toList());
    }

    static List<Integer> convertToLength(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        for (String string : strings) {
            result.add(string.length());
        }
        return result;
    }


}
