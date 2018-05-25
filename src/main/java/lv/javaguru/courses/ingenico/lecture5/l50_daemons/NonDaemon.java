package lv.javaguru.courses.ingenico.lecture5.l50_daemons;

public class NonDaemon implements Runnable {

    @Override
    public void run() {
        while (true){}
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new NonDaemon());
        thread.start();
        System.out.println("end of main");
        System.out.println("program waits while all NON daemon threads not terminated");
    }
}
