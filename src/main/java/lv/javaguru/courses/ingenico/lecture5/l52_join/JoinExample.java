package lv.javaguru.courses.ingenico.lecture5.l52_join;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread updateAllUsers = new Thread(() -> simulateSlowMethod(4000, "update all users"));
        Thread sendNotifications = new Thread(() -> simulateSlowMethod(3000, "send notifications"));
        updateAllUsers.start();
        sendNotifications.start();
        updateAllUsers.join();
        sendNotifications.join();
        log.info("do something after all operations");

    }

    static void simulateSlowMethod(long time, String operation){
        try {
            log.info("{} : start", operation);
            Thread.sleep(time);
            log.info("{} : end", operation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
