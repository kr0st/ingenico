package lv.javaguru.courses.ingenico.lecture5.l51_sleep;

import lv.javaguru.courses.ingenico.lecture5.commoncode.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.function.Consumer;

public class PollingMessageConsumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(PollingMessageConsumer.class);

    private final Queue<Message> messageQueue;
    private final long sleepOnEmptyQueue;

    public PollingMessageConsumer(Queue<Message> messageQueue, long sleepOnEmptyQueue) {
        this.messageQueue = messageQueue;
        this.sleepOnEmptyQueue = sleepOnEmptyQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message = messageQueue.poll();
            if (message == null) {

                try{
                    Thread.sleep(sleepOnEmptyQueue);
                } catch (InterruptedException e){
                    logger.info("interrupted");
                    return;
                }

            } else {
                logger.info("message processing start : {}", message);
                noSleepDelay(1000);
                logger.info("message procesing end : {}", message);
            }

            if (Thread.interrupted()){
                logger.info("interrupted");
                return;
            }
        }
    }

    private void noSleepDelay(int millis){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < millis){}
    }
}
