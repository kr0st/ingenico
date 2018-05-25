package lv.javaguru.courses.ingenico.lecture5.l53_voliatile.counter;

public class Counter {

    private volatile int value;

    public void increment(){
        value++;
    }

    public void decrement(){
        value--;
    }

    public int get(){
        return value;
    }

}
