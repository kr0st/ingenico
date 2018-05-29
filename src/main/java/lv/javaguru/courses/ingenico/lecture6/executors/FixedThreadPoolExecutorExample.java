package lv.javaguru.courses.ingenico.lecture6.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class FixedThreadPoolExecutorExample {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> futureResult = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return "hello";
        });
        //do something else here
        //then get result with timeout
        String result = null;
        try {
            result = futureResult.get(1500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("future completed exceptionally", e);
        }
        log.info("Result from future : {}", result);

        // thread pools are not daemons
        executorService.shutdown();
    }

}
