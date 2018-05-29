package lv.javaguru.courses.ingenico.lecture6.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SchedulledThreadPoolUsage {

    private static final AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        long initialDelay = 500;
        long rate = 1000;
        ScheduledFuture<?> scheduledTask = executorService.scheduleAtFixedRate(
                () -> log.info("task executes : {}", counter.incrementAndGet()),
                initialDelay,
                rate,
                TimeUnit.MILLISECONDS
        );

        TimeUnit.SECONDS.sleep(10);
        scheduledTask.cancel(true);
        executorService.shutdown();
    }

}
