package lv.javaguru.courses.ingenico.lecture5.hometask.solution;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ResuableThreadMain {

    public static void main(String[] args) throws InterruptedException {
        ReusableThread thread = new ReusableThread(new Semaphore(1));
        thread.start();
        thread.setRunnable(() -> System.out.println("first"));
        while (thread.isBusy()){}
        thread.setRunnable(() -> System.out.println("second"));
        thread.interrupt();

        FixedThreadPool pool = new FixedThreadPool(2);
        pool.add(() -> {
            try {
                System.out.println("pool 1");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        pool.add(() -> {
            try {
                System.out.println("pool 2");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        TimeUnit.SECONDS.sleep(3);
        pool.terminateImmediately();
    }

}
