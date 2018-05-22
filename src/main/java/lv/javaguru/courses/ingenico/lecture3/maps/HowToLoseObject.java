package lv.javaguru.courses.ingenico.lecture3.maps;

import java.util.HashMap;
import java.util.Map;

public class HowToLoseObject {

    public static void main(String[] args) {
        Map<A, String> map = new HashMap<>();
        A key = new A("a");
        A copy = new A("a");

        System.out.println(key.equals(copy));
        System.out.println(key == copy);
        map.put(key, "value");

        String s = map.get(copy);
        System.out.println(s);

        map.entrySet().forEach(System.out::println);

    }

    static class A {
        String field;

        public A(String field) {
            this.field = field;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            return field != null ? field.equals(a.field) : a.field == null;
        }

        @Override
        public int hashCode() {
            return field != null ? field.hashCode() : 0;
        }
    }
}
