package lv.javaguru.courses.ingenico.lecture4.streams.t44_limit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Limit {


    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            integers.add(random.nextInt());
        }

        List<Integer> positives = integers.stream()
                                          .filter(i -> i > 0)
                                          .limit(3)
                                          .collect(Collectors.toList());
        positives.forEach(System.out::println);
    }

    //todo : rewrite method with stream
    static List<Integer> selectPositiveNumbers(List<Integer> integers, int maxCount) {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : integers) {
            if (integer > 0 && result.size() < maxCount) {
                result.add(integer);
            }
        }
        return result;
    }

}
