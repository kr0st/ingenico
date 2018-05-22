package lv.javaguru.courses.ingenico.lecture4.l40_lambdas;

import java.util.function.BiFunction;

public class L4BiFunctions {


    public static void main(String[] args) {

        //todo : replace countLetters with lambda

        BiFunction<String, Character, Integer> lettersCounter = (string, letter) -> {
            int count = 0;
            for (char c : string.toCharArray()) {
                if (c == letter) {
                    count++;
                }
            }
            return count;
        };

        //todo : replace countLetters with method reference
        BiFunction<String, Character, Integer> lettersCounter2 = L4BiFunctions::countLetters;

    }

    private static int countLetters(String string, char letter) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }
        return count;
    }
}
