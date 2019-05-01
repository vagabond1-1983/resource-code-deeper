# resource-code-deeper
研究源码工程，先java、spring、tomcat
## java
### java8
#### stream API
- filter
过滤。完成后对象不变
- limit
前几条，短路操作
- map
对象变换
- flatMap
多个流合并为一个流
- findAny, anyMatch
查找
- reduce
归约，对集合进行累加等计算
- parallelStream
并行流，进行并行化处理
- 实例练习
-- Refer: https://github.com/konohiroaki/java8-code-kata
--  [CustomerExamTest](src/main/java/com/vaga/java/stream/kata/exam/CustomerExamTest.java)
#### CompletableFuture
异步框架
返回Future对象，通过get获取结果
主要使用CompletableFuture.supplyAsync进行异步操作。
高级用法中有thenComplete，thenApply等
### 集合源码
#### HashMap
Refer: https://github.com/stalary/Source-code-analysis/blob/master/note/HashMap.md
1. 初始大小：初始容量不指定为16，负载因子为0.75
负载因子的作用是计算最大的容量tableSizeFor(capacity * float_number)
默认最大容量是2的30次方
2. hash：采用了k.hash ^ hash >>> 16，高位补1，低位异或
3. put：数组+链表（当链表长度多于8个，转为红黑树）
4. get：链表查找和红黑树查找
5. resize：扩容时为原本容量的两倍
6. java 8在链表长度为8以上时，用红黑树是为了提升查找性能。因为红黑树本质是平衡二叉树(O(logn))，查找次数优于链表(O(n))
#### Collections
#### ArrayList
非线程安全，null不能作为元素
内部是一个Object数组
1. 初始化：可设置capacity
2. add：先扩容，扩大1.5倍，再增加元素
3. remove：数组移动到前一个位置，把最后一个元素位置设置为null，待GC
4. ensureCapacity：当需要一次插入大量元素时，用这个一次性扩容，这样就不用每次1.5倍扩容了。提升性能。
### String
#### StringBuilder
### 多线程源码
#### [多线程理论](src/main/java/com/vaga/java/concurrent/concurrent.md) 
#### CompletableFuture
#### ArrayBlockingQueue
#### ConcurrentHashMap
#### CountDownLatch
#### CyclicBarrier
### JVM
#### Java8内存模型
主要分成两个部分：堆和方法区是线程共享的，程序计数器、本地方法栈、虚拟机栈是线程独享的
对象实例都是分配在堆上。
方法区是之前的栈，里面存放的是加载的类、常量、静态变量、即时编译器编译后的代码等数据。运行时常量池也在这里。
程序计数器是用来指示程序运行的计数器。
本地方法栈是虚拟机用到的Native方法服务。
虚拟机栈是局部变量表、操作数栈、动态链接、方法出口等信息。
#### GC
- GC对于内存回收基本上是三种策略：标记-清除、复制、标记-整理。
-- 标记-清除：对堆上的对象进行是否可回收的标记，如果没有指向其的引用，则会清除。优点是方法简单执行时间较快，缺点是内存碎片产生较多。
-- 复制：划分成两片区域，可回收空间释放后，复制有效对象到另一片空间中。优点是内存分布比较完整，缺点是需要空间占用大。
-- 标记-整理：对堆上的对象进行标记，如果没有指向其的引用，则整理有效对象到释放的空间中。优点是内存分布完整，缺点是时间长。
- GC一直需要解决的问题就是Stop The World，就是在清理时需要将所有线程暂停，这样对于用户体验来说是不好的。所以才有了不同的收集器。
-- CMS：采用并行线程进行标记，然后清理
-- G1：采用并行线程进行标记，然后整理。
- [GCDetails实验](src/main/java/com/vaga/java/jvm/gc/testAllocation.java)
#### [JConsole实验](src/main/java/com/vaga/java/jvm/jconsole/MonitoringTest.java)
### [LeetCode](src/main/java/com/vaga/java/leetcode)


Refer:
[java源码分析](https://github.com/stalary/Source-code-analysis)
[Java8 kata练习题](https://github.com/konohiroaki/java8-code-kata)
