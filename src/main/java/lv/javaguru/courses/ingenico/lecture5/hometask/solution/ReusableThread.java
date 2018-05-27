package lv.javaguru.courses.ingenico.lecture5.hometask.solution;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j
public class ReusableThread extends Thread {

    private final Object waitLock = new Object();

    private volatile Semaphore semaphore;

    private volatile Runnable runnable;

    public ReusableThread(){}

    public ReusableThread(Runnable runnable, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.runnable = runnable;
    }

    public ReusableThread(Runnable runnable) {
        this(runnable, null);
    }

    public ReusableThread(Semaphore semaphore){
        this(null, semaphore);
    }

    @Override
    public void run() {
        while (true) {
            try {
                doRun();
            } catch (InterruptedException e) {
                log.debug("interrupted");
                return;
            }
        }
    }

    private void doRun() throws InterruptedException {
        synchronized (waitLock) {
            if (runnable == null) {
                waitLock.wait();
            }
            if (isInterrupted()) {
                throw new InterruptedException();
            }
            semaphore.acquire();
            runnable.run();
            runnable = null;
            semaphore.release();
        }
    }

    public boolean isBusy() {
        return runnable != null;
    }

    public void setRunnable(Runnable runnable) {
        if (isBusy()) {
            throw new IllegalStateException("execution in progress, check status with isBusy()");
        }
        synchronized (waitLock) {
            this.runnable = runnable;
            waitLock.notify();
        }
    }

    public void setSemaphore(Semaphore semaphore){
        if (isBusy()){
            throw new IllegalStateException("execution in progress, check status with isBusy()");
        }
        this.semaphore = semaphore;
    }

}
