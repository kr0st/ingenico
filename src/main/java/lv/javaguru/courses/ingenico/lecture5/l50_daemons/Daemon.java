package lv.javaguru.courses.ingenico.lecture5.l50_daemons;

import java.util.concurrent.TimeUnit;

public class Daemon implements Runnable {

    @Override
    public void run() {
        while (true){}
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Daemon());
        thread.setDaemon(true);
        thread.start();
        System.out.println("end of main, program terminates");
    }
}
