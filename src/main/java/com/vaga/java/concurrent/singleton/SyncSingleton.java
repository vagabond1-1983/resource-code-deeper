package com.vaga.java.concurrent.singleton;

/**
 * @Description 线程安全单例，用synchronized保证拿到的是同一个实例
 * @Date 2019/4/20 21:29
 * @Version 1.0
 **/
public class SyncSingleton implements ISingleton{
    private static volatile SyncSingleton singleton = null;

    private SyncSingleton() {
    }

    public static ISingleton getInstance() {

        if (null == singleton) {
            synchronized (SyncSingleton.class) {
                if (null == singleton) {
                    singleton = new SyncSingleton();
                }
            }
        }
        return singleton;
    }

}
