package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s0_counter;

public class SynchronizedCounter {

    private volatile int value;

    public synchronized void increment(){
        value++;
    }

    public synchronized void decrement(){
        value--;
    }

    public int get(){
        return value;
    }

}
