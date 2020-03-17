package com.vaga.java.concurrent.thread;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author vaga
 * @version 2020/3/17 10:15 上午
 * @description 两个线程修改同一值
 */
public class ModifySameValueTest {
    ReentrantLock lock = new ReentrantLock();
    int i = 1;
    @Test(threadPoolSize = 10, invocationCount = 100)
    public void run() throws InterruptedException {
//        modify();


        modifyWithoutLock();
    }

    // lock way to make i having correct value
    private void modify() throws InterruptedException {

        lock.lock();
        Thread.sleep(500);
        i += 10;
        System.out.println(Thread.currentThread().getName() + " " + i);
        // thread 1: i = 11
        // thread 2: i = 21
        lock.unlock();
    }

    volatile AtomicInteger vi = new AtomicInteger(1);

    // volatile to force thread getting the value from main memory
    private void modifyWithoutLock() throws InterruptedException {
        Thread.sleep(500);
        vi.getAndAdd(10);
        System.out.println(Thread.currentThread().getName() + " " + vi);
    }
}
