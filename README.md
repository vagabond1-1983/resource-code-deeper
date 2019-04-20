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
#### HashMap 源码学习
Refer: https://github.com/stalary/Source-code-analysis/blob/master/note/HashMap.md
1. 初始大小：初始容量不指定为16，负载因子为0.75
负载因子的作用是计算最大的容量tableSizeFor(capacity * float_number)
默认最大容量是2的30次方
2. hash：采用了k.hash ^ hash >>> 16，高位补1，低位异或
3. put：数组+链表（当链表长度多于8个，转为红黑树）
4. get：链表查找和红黑树查找
5. resize：扩容时为原本容量的两倍
6. java 8在链表长度为8以上时，用红黑树是为了提升查找性能。因为红黑树本质是平衡二叉树(O(logn))，查找次数优于链表(O(n))
#### ConcurrentHashMap
#### Collections
#### ArrayList
### String
#### StringBuilder
### 多线程源码
#### [多线程理论](src/main/java/com/vaga/java/concurrent/concurrent.md) 
#### CompletableFuture
#### ArrayBlockingQueue
#### CountDownLatch
#### CyclicBarrier
### JVM
### [LeetCode](src/main/java/com/vaga/java/leetcode)


Refer:
[java源码分析](https://github.com/stalary/Source-code-analysis)
[Java8 kata练习题](https://github.com/konohiroaki/java8-code-kata)