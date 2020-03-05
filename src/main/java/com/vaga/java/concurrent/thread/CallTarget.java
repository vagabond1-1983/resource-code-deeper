package com.vaga.java.concurrent.thread;

import java.util.concurrent.Callable;

/**
 * @author vaga
 * @version 2020/3/5 10:33 上午
 * @description callable实现，多线程
 */
public class CallTarget implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "线程执行了call方法");
        Thread.sleep(500);
        return 1;
    }
}
