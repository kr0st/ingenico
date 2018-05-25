package lv.javaguru.courses.ingenico.lecture5.l53_voliatile.valueholder;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RunnerMain {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Runner runner = new Runner();
            Thread thread = new Thread(runner);
            thread.start();
            runner.flag = false;
        }

    }

}
