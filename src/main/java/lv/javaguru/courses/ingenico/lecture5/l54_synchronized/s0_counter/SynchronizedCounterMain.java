package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s0_counter;

public class SynchronizedCounterMain {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        Thread incrementer = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronizedCounter.increment();
            }
        });
        Thread decrementer = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronizedCounter.decrement();
            }
        });
        incrementer.start();
        decrementer.start();
        incrementer.join();
        decrementer.join();
        System.out.println(synchronizedCounter.get());
    }

}
