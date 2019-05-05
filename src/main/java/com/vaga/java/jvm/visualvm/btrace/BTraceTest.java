package com.vaga.java.jvm.visualvm.btrace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Description BTrace追踪
 * 先下载visualvm，我下载了1.4.2，然后安装btrace插件，运行此代码
 * 在visualvm中打开左侧进程，btrace并没有在默认的页签中，右键进程点Trace Application...
 * 写入代码
 * <code>
    import com.sun.btrace.annotations.*;
    import static com.sun.btrace.BTraceUtils.*;

    @BTrace
    public class TracingScript {
        @OnMethod(clazz="com.vaga.java.jvm.visualvm.btrace.BTraceTest", method="add", location=@Location(Kind.RETURN))
        public static void func(@Self com.vaga.java.jvm.visualvm.btrace.BTraceTest instance, int a, int b, @Return int result) {
            println("调用堆栈:");
            jstack();
            println(strcat("A:",str(a)));
            println(strcat("B:",str(b)));
            println(strcat("result:",str(result)));
        }
    }
 * </code>
 * @Date 2019/5/1 19:59
 * @Version 1.0
 **/
public class BTraceTest {
    public int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) throws IOException {
        BTraceTest traceTest = new BTraceTest();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            reader.readLine();
            int a = (int) Math.round(Math.random() * 1000);
            int b = (int) Math.round(Math.random() * 1000);
            System.out.println(traceTest.add(a, b));
        }
    }
}
