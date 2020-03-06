package com.vaga.java.concurrent.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author vaga
 * @version 2020/3/6 2:22 下午
 * @description 模拟死锁
 * 线程 1 get res1
 * 线程 2 get res2
 * 线程 1 waiting get res2
 * 线程 2 waiting get res1
 * 线程 1和线程 2都阻塞了，程序不往下进行了
 */
public class DeadLock {
    private static String res1 = "res1";
    private static String res2 = "res2";


    public static void main(String[] args) {
//        syncThread();
////        dl();
//        solveDL();

        // =============用ReadWriteLock解决资源竞争的问题，注意需要是同一把锁

        lockThread();
    }

    private static void lockThread() {
        new Thread(() -> {
            readwriteLock();
        }, "线程1").start();
        new Thread(() -> {
            readwriteLock();
        }, "线程2").start();
    }

    private static void readwriteLock() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " get " + res1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.readLock().unlock();
        System.out.println(Thread.currentThread().getName() + " waiting to get " + res2);
        lock.readLock().lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " get " + res2);
        lock.readLock().unlock();
    }

    private static void solveDL() {
        new Thread(() -> {
            synchronized (res1) {
                System.out.println(Thread.currentThread().getName() + " get " + res1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting get " + res2);
                synchronized (res2) {
                    System.out.println(Thread.currentThread().getName()+" get " + res2);
                }
            }
        }, "线程 2").start();
    }

    private static void dl() {
        new Thread(() -> {
            synchronized (res2) {
                System.out.println(Thread.currentThread().getName() + " get " + res2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting get " + res1);
                synchronized (res1) {
                    System.out.println(Thread.currentThread().getName() + " get " + res1);
                }
            }
        }, "线程 2").start();
    }

    private static void syncThread() {
        new Thread(() -> {
            synchronized (res1) {
                System.out.println(Thread.currentThread().getName() + " get " + res1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " waiting get " + res2);
                synchronized (res2) {
                    System.out.println(Thread.currentThread().getName() + " get " + res2);
                }
            }
        }, "线程 1").start();
    }
}
