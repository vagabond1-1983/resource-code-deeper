package com.vaga.java.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * @author vaga
 * @version 2020/3/8 10:06 上午
 * @description threadlocal
 */
public class ThreadLocalExample implements Runnable{
    private static ThreadLocal<SimpleDateFormat> formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-mm-dd"));

    public static void main(String[] args)  {
        ThreadLocalExample example = new ThreadLocalExample();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(example, ""+i);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " default format: " + formatter.get().toPattern());
        try {
            Thread.sleep(1000);
            formatter.set(new SimpleDateFormat());
            System.out.println(Thread.currentThread().getName() + " format changed to "+formatter.get().toPattern());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
