package lv.javaguru.courses.ingenico.lecture4.streams.t46_collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringJoining {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("world");
        strings.add("!");

        String result = strings.stream().collect(Collectors.joining(","));

        System.out.println(result);
    }

    //todo : rewrite with stream
    static String joinStrings(List<String> strings, String delimiter){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            sb.append(strings.get(i));
            if (i < strings.size()){
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }



}
