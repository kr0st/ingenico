package lv.javaguru.courses.ingenico.lecture5.l51_sleep;

import lv.javaguru.courses.ingenico.lecture5.commoncode.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Queue<Message> queue = new LinkedList<>();
        queue.add(new Message<>(UUID.randomUUID(), "string payload"));
        queue.add(new Message<>(UUID.randomUUID(), 4));

        PollingMessageConsumer consumer = new PollingMessageConsumer(queue, 5000L);
        Thread thread = new Thread(consumer);
        thread.start();
        thread.interrupt();
    }
}
