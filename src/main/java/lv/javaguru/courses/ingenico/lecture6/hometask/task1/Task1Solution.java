package lv.javaguru.courses.ingenico.lecture6.hometask.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class Task1Solution
{
    public static void main(String[] args)
    {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        char[] result = forkJoinPool.invoke(new RandomBytesGenerator(100));
        log.info(new Integer(result.length).toString());
    }
}
