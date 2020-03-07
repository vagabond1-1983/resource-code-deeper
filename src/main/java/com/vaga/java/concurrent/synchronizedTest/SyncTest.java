package com.vaga.java.concurrent.synchronizedTest;

import org.testng.annotations.Test;

/**
 * @author vaga
 * @version 2020/3/7 6:03 下午
 * @description synchroized关键字理解
 */
public class SyncTest {
    // javap -c -s -v -l SyncTest.class
    public void method() {
        synchronized (this) {
            System.out.println("synchronized 代码块");
        }
    }
    public static synchronized void singleRun() {
        try {
            System.out.println(Thread.currentThread().getName()+" waiting to run ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void syncInstanceRun() {
        try {
            System.out.println(Thread.currentThread().getName()+" waiting to run ");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void syncCodeRun() {
        System.out.println(Thread.currentThread().getName()+" waiting to run ");
        try {
            Thread.sleep(1000);
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " run");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test(invocationCount = 10)
    public void singleRunTest() {
        // 1. synchronized修饰静态方法
        new Thread(() -> singleRun(), "t1").start();
        new Thread(() -> singleRun(), "t2").start();
        waitFor(2000);
    }

    @Test(invocationCount = 10)
    public void syncInstanceRunTest() {
        // 2. synchronized修饰实例方法
        new Thread(() -> new SyncTest().syncInstanceRun(), "t1").start();
        new Thread(() -> new SyncTest().syncInstanceRun(), "t2").start();
        waitFor(2000);
    }

    @Test(invocationCount = 10)
    public void syncCodeRunTest() {
        // 3. synchronized修饰代码块
        new Thread(() -> new SyncTest().syncCodeRun(), "t1").start();
        new Thread(() -> new SyncTest().syncCodeRun(), "t2").start();
        waitFor(2000);
    }


    public void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
