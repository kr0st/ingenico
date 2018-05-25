package lv.javaguru.courses.ingenico.lecture5.l53_voliatile.counter;

public class CounterMain {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        });
        Thread decrementer = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.decrement();
            }
        });
        incrementer.start();
        decrementer.start();
        incrementer.join();
        decrementer.join();
        System.out.println(counter.get());
    }

}
