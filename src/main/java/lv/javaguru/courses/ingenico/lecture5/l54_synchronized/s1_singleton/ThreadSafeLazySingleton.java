package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s1_singleton;

public class ThreadSafeLazySingleton {

    private static ThreadSafeLazySingleton instance = null;

    public static ThreadSafeLazySingleton getInstance() {
        if (instance == null){
            synchronized (ThreadSafeLazySingleton.class){
                if (instance == null){
                    //read resources and make initialization
                    instance = new ThreadSafeLazySingleton();
                }
            }
        }
        return instance;
    }

}
