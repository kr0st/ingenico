package lv.javaguru.courses.ingenico.lecture5.l54_synchronized.s2_multicounter;

public class MultiCounter {

    private volatile int readCount;
    private volatile int writeCount;

    private final Object readCountLock = new Object();
    private final Object writeCountLock = new Object();

    public void incerementRead(){
        synchronized (readCountLock){
            readCount++;
        }
    }

    public int getReadCount(){
        synchronized (readCountLock){
            return readCount;
        }
    }

    public void incrementWrite(){
        synchronized (writeCountLock){
            writeCount++;
        }
    }

    public int getWriteCount(){
        synchronized (writeCountLock){
            return writeCount;
        }
    }

}
