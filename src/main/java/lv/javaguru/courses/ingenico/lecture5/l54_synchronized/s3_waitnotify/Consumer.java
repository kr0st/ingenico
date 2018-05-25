package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s3_waitnotify;


import lombok.extern.slf4j.Slf4j;
import lv.javaguru.courses.ingenico.lecture5.commoncode.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Consumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final Queue<Message> messageQueue;

    public Consumer(Queue<Message> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while(true){
            try {
                consume();
            } catch (InterruptedException e){
                log.error("interrupted", e);
                return;
            }
        }
    }

    private void consume() throws InterruptedException {
        synchronized (messageQueue){
            while (messageQueue.isEmpty()){
                log.debug("message queue is empty, wait");
                messageQueue.wait();
            }
            Message message = messageQueue.poll();
            if (message != null) {
                log.debug("consumed : {}", message.getPayload());
                messageQueue.notifyAll();
            }
        }
    }
}
