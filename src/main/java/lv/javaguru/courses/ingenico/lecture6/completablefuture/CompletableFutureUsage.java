package lv.javaguru.courses.ingenico.lecture6.completablefuture;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CompletableFutureUsage {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> op1 = CompletableFuture.supplyAsync(() -> longOperation11())
                                                         .thenApply(result -> longOperation12(result));
        CompletableFuture<String> op2 = CompletableFuture.supplyAsync(() -> longOperation2());

        CompletableFuture<String> op3 = op1
                .thenCombine(op2, (result1, result2) -> longOperation3(result1, result2))
                .exceptionally(throwable -> fallback(throwable));

        CompletableFuture<Void> result = op3.thenAccept(result3 -> updateUI(result3));

        result.get();
    }

    private static String longOperation11() {
        log.info("operation 1.1, sleep 500 millis");
        sleep(500);
        return "operation 1.1 completed";
    }

    private static String longOperation12(String operation11Result) {
        log.info("operation 1.2 = {}, sleep 500 millis", operation11Result);
        sleep(500);
        return "operation 1.2 completed";
    }

    private static String longOperation2() {
        log.info("operation 2, sleep 1000 millis");
        sleep(1000);
        return "operation 2 completed";
    }

    private static String longOperation3(String operation1Result, String operation2Result) {
        log.info("combine two results, sleep 500 millis");
        sleep(500);
        return "operation 3 completed";
    }

    private static void updateUI(String operation3Result) {
        log.info("update ui");
    }

    private static String fallback(Throwable throwable) {
        return "achtung! something wrong!";
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
