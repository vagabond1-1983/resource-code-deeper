# java面试
## 基础
### Integer的缓存值可以设置吗，怎么设置
IntegerCache设置了一个缓存整数的数组，默认是-128 ~ 127。可以通过JVM参数java.lang.Integer.IntegerCache.high更改缓存的大小。最大不会超过Integer.MAX_VALUE - (-low) -1。
第一次加载时IntegerCache会将缓存数组创建好。
### 抽象类
抽象类不能被实例化。抽象类的构造器不能用于创建实例，是仅提供给子类调用的。
### String和StringBuffer、StringBuilder的区别
String是final的，不可变，不能被继承。
会产生临时对象，执行效率低
StringBuffer的读写都用了synchroized修饰方法，线程安全的。StringBuilder不是。
### [需要同时重写equals和hashcode方法](../src/main/java/com.vaga.java.basic.EqualsAndHashCodeRewrite)
## 容器
### ArrayList的删除操作需要用Iterator迭代器进行。不能直接用list.remove(i)方法删除。具体原因可以看[这篇文章](http://notfound9.github.io/interviewGuide/#/docs/ArrayList?id=arraylist%e9%81%8d%e5%8e%86%e6%97%b6%e5%88%a0%e9%99%a4%e5%85%83%e7%b4%a0%e6%9c%89%e5%93%aa%e4%ba%9b%e6%96%b9%e6%b3%95%ef%bc%9f)
### HashMap
- 添加键值对的过程
![添加键值对流程图](http://notfound9.github.io/interviewGuide/static/2.png)
一：hash值不同，直接resize，然后插入；
二：hash值相同并且key equals，直接替换值；
三：hash值相同但是key不equals，则取出此位置上的第一个元素；
四：如果是TreeNode，说明是红黑树，则在红黑树上插入；
五：如果不是TreeNode，需要考虑节点容量是否大于7，大于7则转为红黑树后插入，不大于7则直接插入
### ConcurrentHashMap
1. 线程安全 2. key和value不能是null 3. CAS和synchronize实现线程安全，也是数组+链表+红黑树
## 多线程
### 进程和线程的区别
进程是系统资源分配的最小单位，一个进程可以有多个线程组成。进程会有单独的内存空间，最大4G。
### [多线程进阶](../src/main/java/com/java/concurrent/concurrent.md)

参考资料:
[面试指北](http://notfound9.github.io/interviewGuide/#/)
[JavaGuide](https://github.com/Snailclimb/JavaGuide)