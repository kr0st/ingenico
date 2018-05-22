package lv.javaguru.courses.ingenico.lecture3.lists;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ArrayListVsLinkedList {

    public static void main(String[] args) {
        System.out.println("--- Add last");
        testAddLast(new ArrayList<>());
        testAddLast(new LinkedList<>());

        System.out.println("\n--- Add first");
        testAddFirst(new ArrayList<>());
        testAddFirst(new LinkedList<>());

        System.out.println("\n--- Add middle");
        testAddMiddle(new ArrayList<>());
        testAddMiddle(new LinkedList<>());

    }

    private static void testAddLast(List<Integer> integers){
        long time = 0;
        int times = 1_000_000;
        for (int i = 0; i < times; i++) {
            time += addLast(integers);
        }
        System.out.printf("%s.add(last) to filled list %,d times = %,d ms (total)\n", integers.getClass().getSimpleName(), times, time);
    }

    private static void testAddFirst(List<Integer> integers){
        long time = 0;
        int times = 100_000;
        for (int i = 0; i < times; i++) {
            time += addFirst(integers);
        }
        System.out.printf("%s.add(first) to filled list %,d times = %,d ms (total)\n", integers.getClass().getSimpleName(), times, time);
    }

    private static void testAddMiddle(List<Integer> integers){
        long time = 0;
        int times = 100_000;
        for (int i = 0; i < times; i++) {
            time += addMiddle(integers);
        }
        System.out.printf("%s.add(middle) to filled list %,d times = %,d ms (total)\n", integers.getClass().getSimpleName(), times, time);
    }

    private static long addLast(List<Integer> integers){
        long start = System.currentTimeMillis();
        integers.add(Integer.MAX_VALUE);
        return System.currentTimeMillis() - start;
    }

    private static long addFirst(List<Integer> integers){
        long start = System.currentTimeMillis();
        integers.add(0, Integer.MAX_VALUE);
        return System.currentTimeMillis() - start;
    }

    private static long addMiddle(List<Integer> integers){
        long start = System.currentTimeMillis();
        integers.add(integers.size() / 2, Integer.MAX_VALUE);
        return System.currentTimeMillis() - start;
    }

}
