package lv.javaguru.courses.ingenico.lecture5.hometask.solution;


import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class FixedThreadPool {

    private final Object waitLock;

    private volatile Queue<Runnable> tasks;

    private volatile ReusableThread[] threads;

    private volatile ThreadsExecutor threadsExecutor;

    private volatile boolean terminated = false;

    public FixedThreadPool(int size) {
        this.waitLock = new Object();
        this.tasks = new ConcurrentLinkedQueue<>();
        Semaphore semaphore = new Semaphore(size);
        this.threadsExecutor = new ThreadsExecutor(semaphore);
        this.threadsExecutor.start();
        this.threads = new ReusableThread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new ReusableThread(semaphore);
            threads[i].start();
        }
    }

    public synchronized void add(Runnable runnable) {
        if (terminated) {
            throw new IllegalStateException("terminated");
        }
        synchronized (waitLock) {
            tasks.add(runnable);
            waitLock.notifyAll();
        }
    }

    public synchronized void terminateImmediately() {
        if (terminated) {
            return;
        }
        terminated = true;
        threadsExecutor.interrupt();
        for (ReusableThread thread : threads) {
            thread.interrupt();
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    private class ThreadsExecutor extends Thread {

        volatile Semaphore semaphore;

        ThreadsExecutor(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            synchronized (waitLock) {
                while (true) {
                    try {
                        checkInterruption();
                        doRun();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        }

        void doRun() throws InterruptedException {
            if (tasks.isEmpty()) {
                waitLock.wait();
            }
            try {
                semaphore.acquire();
                resurrectDeadThreads();
                checkInterruption();
                tryRunNext();
                checkInterruption();
            } finally {
                semaphore.release();
            }
        }

        void checkInterruption() throws InterruptedException {
            if (isInterrupted()) {
                throw new InterruptedException();
            }
        }

        int findFreeThread() {
            for (int i = 0; i < threads.length; i++) {
                if (!threads[i].isBusy()) {
                    return i;
                }
            }
            return -1;
        }

        void resurrectDeadThreads() {
            for (int i = 0; i < threads.length; i++) {
                if (!threads[i].isAlive()) {
                    ReusableThread thread = new ReusableThread(semaphore);
                    thread.start();
                    threads[i] = thread;
                }
            }
        }

        void tryRunNext() {
            int index = findFreeThread();
            if (index < 0) {
                return;
            }
            ReusableThread thread = threads[index];
            thread.setRunnable(tasks.poll());
        }
    }


}
