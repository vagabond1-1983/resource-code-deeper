# JVM
## Java8内存模型
![内存模型](https://camo.githubusercontent.com/a66819fd82c6adfa69b368edf3c52b1fa9cdc89d/68747470733a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f323031392d332f4a564de8bf90e8a18ce697b6e695b0e68daee58cbae59f9f2e706e67)
主要分成两个部分：堆和方法区是线程共享的，程序计数器、本地方法栈、虚拟机栈是线程独享的
- 对象实例都是分配在堆上。堆可以细分为新生代和老年代。老年代(tentired)其实就是长期存在的对象，回收概率小。新生代生命周期短，一般新分配的对象在新生代。
    新生代又可分：Eden空间、From Survivor空间、To Survivor空间。
- 方法区是之前的栈，里面存放的是加载的类、常量、静态变量、即时编译器编译后的代码等数据。运行时常量池也在这里。
- 程序计数器是用来指示程序运行的计数器。
- 本地方法栈是虚拟机用到的Native方法服务。
- 虚拟机栈是局部变量表、操作数栈、动态链接、方法出口等信息。
### GC
- GC对于内存回收基本上是三种策略：标记-清除、复制、标记-整理。
-- 标记-清除：对堆上的对象进行是否可回收的标记，如果没有指向其的引用，则会清除。优点是方法简单执行时间较快，缺点是内存碎片产生较多。
-- 复制：划分成两片区域，可回收空间释放后，复制有效对象到另一片空间中。优点是内存分布比较完整，缺点是需要空间占用大。
-- 标记-整理：对堆上的对象进行标记，如果没有指向其的引用，则整理有效对象到释放的空间中。优点是内存分布完整，缺点是时间长。
- GC一直需要解决的问题就是Stop The World，就是在清理时需要将所有线程暂停，这样对于用户体验来说是不好的。所以才有了不同的收集器。
-- CMS：采用并行线程进行标记，然后清理
-- G1：采用并行线程进行标记，然后整理。
- [GCDetails实验](../../src/main/java/com/vaga/java/jvm/gc/testAllocation.java)
## [JConsole实验](../../src/main/java/com/vaga/java/jvm/jconsole/MonitoringTest.java)
## [visualvm - btrace](../../src/main/java/com/vaga/java/jvm/visualvm/btrace/BTraceTest.java)
## 类加载模型
- loadClass是java定义好的，findClass可重写
- 双亲委派机制是父类先加载类，无法加载再由子类加载。顺序：
BootStrap ClassLoader->Extension ClassLoader->Application ClassLoader->User ClassLoader
- 破坏双亲委派是为了更加灵活的自定义加载器，不再先从父类开始加载。
## 并发时内存
- volatile：防止指令重排序
- 线程实现可分为：系统线程、用户线程和用户线程加轻量级系统进程
- 线程状态共有五种：启动、运行、等待、阻塞、终止。启动和终止是单向的，其他都是双向的。