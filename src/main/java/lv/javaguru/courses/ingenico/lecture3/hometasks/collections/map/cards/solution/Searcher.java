package lv.javaguru.courses.ingenico.lecture3.hometasks.collections.map.cards.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Searcher {

    public static <T> T findOneBy(Iterable<T> iterable, Predicate<? super T> predicate){
        for (T t : iterable) {
            if (predicate.test(t)){
                return t;
            }
        }
        return null;
    }

    public static <T> List<T> findManyBy(Iterable<T> iterable, Predicate<? super T> predicate){
        List<T> result = new ArrayList<>();
        for (T t : iterable) {
            if (predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }

}
