package lv.javaguru.courses.ingenico.lecture4.streams.t45_flatmap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("john");
        strings.add("peter");
        Set<String> uniqueCharacters = strings.stream()
                                              .flatMap(string -> Stream.of(string.split("")))
                                              .collect(Collectors.toSet());
        System.out.println(uniqueCharacters);
    }

    static Set<String> uniqueCharacters(List<String> strings) {
        Set<String> characters = new HashSet<>();
        for (String string : strings) {
            String[] chars = string.split("");
            for (String c : chars) {
                characters.add(c);

            }
        }
        return characters;
    }

}
