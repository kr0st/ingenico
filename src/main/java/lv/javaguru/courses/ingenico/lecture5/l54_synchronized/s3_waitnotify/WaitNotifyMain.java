package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s3_waitnotify;

import lv.javaguru.courses.ingenico.lecture5.commoncode.Message;

import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyMain {

    public static void main(String[] args) {
        Queue<Message> taskQueue = new LinkedList<>();
        Thread producer = new Thread(new Producer(taskQueue, 5));
        Thread consumer = new Thread(new Consumer(taskQueue));
        producer.start();
        consumer.start();
    }

}
