package com.vaga.java.jvm.jconsole;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 使用JConsole观察jvm情况
 * 打开jdk/bin下的jconsole.exe，运行测试类，选中此java进程
 * vm args: -Xms100M -Xmx100M -XX:+UseSerialGC
 * @Date 2019/5/1 19:33
 * @Version 1.0
 **/
public class MonitoringTest {
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
