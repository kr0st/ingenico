package lv.javaguru.courses.ingenico.lecture6.hometask.solution;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        byte[] result = forkJoinPool.invoke(new RandomBytesGenerator(100));
        BigInteger bigInteger = new BigInteger(result);
        System.out.println(bigInteger.toString(16));
    }

}
