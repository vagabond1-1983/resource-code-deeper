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


参考资料
[面试指北](http://notfound9.github.io/interviewGuide/#/)