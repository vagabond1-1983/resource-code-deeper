# java面试
## [leetcode](leetcode)
## 基础
### Integer的缓存值可以设置吗，怎么设置
IntegerCache设置了一个缓存整数的数组，默认是-128 ~ 127。可以通过JVM参数java.lang.Integer.IntegerCache.high更改缓存的大小。最大不会超过Integer.MAX_VALUE - (-low) -1。
第一次加载时IntegerCache会将缓存数组创建好。
### 抽象类
抽象类不能被实例化。抽象类的构造器不能用于创建实例，是仅提供给子类调用的。
### String和StringBuffer、StringBuilder的区别
String是final的，不可变，不能被继承。会产生临时对象，执行效率低。

StringBuffer的读写都用了synchroized修饰方法，线程安全的。StringBuilder不是。
### [需要同时重写equals和hashcode方法](../src/main/java/com.vaga.java.basic.EqualsAndHashCodeRewrite)
## 容器
### [容器属性的测试](../src/main/java/com/vaga/java/collection/NullValueTest.java)
### ArrayList的删除操作需要用Iterator迭代器进行。不能直接用list.remove(i)方法删除。具体原因可以看[这篇文章](http://notfound9.github.io/interviewGuide/#/docs/ArrayList?id=arraylist%e9%81%8d%e5%8e%86%e6%97%b6%e5%88%a0%e9%99%a4%e5%85%83%e7%b4%a0%e6%9c%89%e5%93%aa%e4%ba%9b%e6%96%b9%e6%b3%95%ef%bc%9f)
如果用foreach循环，删除用list.remove(o)的方式，会导致ConcurrentModifiedException。
因为foreach循环list，本质上是用iterator的hasNext()和next()。在remove(o)时，会调用fastRemove(index)进行删除，这个方法会将modCount++。
这样在hasNext()判断modCount是否和expectedModCount一致时，会抛出异常。不允许这样的修改。
所以用Iterator时，它的remove()方法是会更新expectedModCount值等于modCount的。所以不会出现问题。
### HashMap
- 添加键值对的过程
![添加键值对流程图](http://notfound9.github.io/interviewGuide/static/2.png)
一：tab为空直接resize，然后插入；
二：hash值不同，直接插入；
三：hash值相同并且tab i的key equals，直接替换值；
四：hash值相同但是key不equals，则判断tab i是否是TreeNode；
五：如果是TreeNode，说明是红黑树，则在红黑树中遍历查找key是否相同，相同则替换，不同则插入；
六：如果不是TreeNode，遍历查找key是否有相同，有则替换，无则插入，之后再判断节点容量是否大于8，大于则转为红黑树
七：最后判断++size是否超出阈值，也就是容量的0.75倍（默认），超出则resize，扩大一倍容量。
### ConcurrentHashMap
1. 线程安全 2. key和value不能是null 3. CAS和synchronize实现线程安全，也是数组+链表+红黑树
## [多线程](concurrent)

## [java8](java8)
## [jvm](jvm)


## 源码编程题
- 设计一个迭代器，包含hasNext(),next(),remove()三个方法。注意多线程问题

参考资料:
[面试指北](http://notfound9.github.io/interviewGuide/#/)
[JavaGuide](https://github.com/Snailclimb/JavaGuide)