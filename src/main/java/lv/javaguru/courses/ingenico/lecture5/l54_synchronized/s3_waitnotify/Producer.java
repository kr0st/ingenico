package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s3_waitnotify;


import lombok.extern.slf4j.Slf4j;
import lv.javaguru.courses.ingenico.lecture5.commoncode.Message;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Producer implements Runnable {

    private final Queue<Message> messageQueue;
    private final int maxCapacity;
    private int counter;

    public Producer(Queue<Message> messageQueue, int maxCapacity) {
        this.messageQueue = messageQueue;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public void run() {
        while(true){
            try {
                produce();
            } catch (InterruptedException e){
                log.error("interrupted", e);
                return;
            }
        }
    }

    private void produce() throws InterruptedException {
        synchronized (messageQueue){
            while (messageQueue.size() >= maxCapacity){
                log.debug("message queue full, wait");
                messageQueue.wait();
            }
            messageQueue.add(new Message<>(UUID.randomUUID(), ++counter));
            log.debug("produced : {}", counter);
            messageQueue.notify();
        }
    }
}
