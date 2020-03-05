package com.vaga.java.concurrent.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author vaga
 * @version 2020/3/5 10:35 上午
 * @description 调用callable
 */
public class DryRun {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + "主线程执行");
        CallTarget callTarget = new CallTarget();
        FutureTask<Integer> task = new FutureTask<Integer>(callTarget);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }
}
