package lv.javaguru.courses.ingenico.lecture5.l53_voliatile.valueholder;

public class Runner implements Runnable{

    boolean flag = true;
    int count = 0;

    @Override
    public void run() {
        while (flag){
            count++;
        }
        System.out.println(count);
    }
}
