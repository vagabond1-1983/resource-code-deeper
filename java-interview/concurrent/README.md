# 多线程
## 进程和线程的区别
进程是系统资源分配的最小单位，一个进程可以有多个线程组成。进程会有单独的内存空间，最大4G。
## 实现多线程的几种方式
1. 继承Thread，重写run方法
2. 实现Runnable接口，重写run方法。
但是前两个都不能设置返回值
3. [实现Callable接口，可以有返回值](../../src/main/java/com/vaga/java/concurrent/thread/CallTarget.java)
## 线程的生命周期和状态
![线程生命周期](https://camo.githubusercontent.com/e518e038e37c2d27abb394b00b438d347466c90c/68747470733a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f31392d312d32392f4a6176612b2545372542412542462545372541382538422545372538412542362545362538302538312545352538462539382545382542462538312e706e67)
线程分为：开始-运行-等待-阻塞-超时等待-结束。超时等待就是设定好时间，超出后进入运行。
## 什么是死锁，如何避免
多个线程同时被阻塞，等待获取某个资源的释放。看下面的图会更清晰些
![死锁图](https://camo.githubusercontent.com/3903a4dc24008be52f72bad23498808b5a743c35/68747470733a2f2f6d792d626c6f672d746f2d7573652e6f73732d636e2d6265696a696e672e616c6979756e63732e636f6d2f323031392d342f323031392d34254536254144254242254539253934253831312e706e67)
请看这个[例子](../../src/main/java/com/vaga/java/concurrent/thread/DeadLock.java)
避免的四个条件
```
破坏互斥条件
这个条件我们没有办法破坏，因为我们用锁本来就是想让他们互斥的（临界资源需要互斥访问）。
破坏请求与保持条件
一次性申请所有的资源。
破坏不剥夺条件
占用部分资源的线程进一步申请其他资源时，如果申请不到，可以主动释放它占有的资源。
破坏循环等待条件
靠按序申请资源来预防。按某一顺序申请资源，释放资源则反序释放。破坏循环等待条件。
```
## 为什么我们调用start()方法时会执行run()方法，而不是直接调用run()方法
调用start会自动执行run，而调用run还是主线程执行的run方法。
## synchronized
原理是：
当用synchronized锁住一段代码时，编译器会加入monitor，进入时是monitorenter，退出时是monitorexit。
所以当计数器不为0，则表示有线程在访问资源，则不允许其他线程访问。退出后，计数器-1，其他线程拿到锁。
monitor是每个对象都有的特性。（具体再细看）
这样会让synchronized变成同步操作。
synchronized在1.8后进行了优化，使用轻量级锁非都是重量级锁进行。
### [三种修饰方法](../../src/main/java/com/vaga/java/concurrent/synchronizedTest/SyncTest.java)
1. 修饰静态方法
2. 修饰实例方法
3. 修饰代码块，指定加锁对象
## [写一个线程安全的单例](../../src/main/java/com/vaga/java/concurrent/singleton)
## volatile
为什么用这个修饰变量？
JVM有指令重排的优化。当程序是单线程时，指令重排对任务无影响。但是多线程时会出现一个线程拿到的实例并未初始化完成的情况。
所以需要用volatile修饰共享变量。保证对象在多线程情况下的安全。
volatile另一个作用是保证变量的可见性。
## ThreadLocal
[举例](../../src/main/java/com/vaga/java/concurrent/thread/ThreadLocalExample.java)
ThreadLocal可以保证每个线程都会有自己的本地变量，可见度只在自己的线程中。
ThreadLocal存储是用ThreadLocalMap实现的，每个Thread都会具有一个ThreadLocalMap。key是ThreadLocal，value是存放的对象。

## 线程池
用ThreadPoolExecutor创建，而不要用Executor创建。当达到设定的核心线程数，则其他的任务进入等待队列。等到队列中的任务都运行完成，再执行等待队列的任务。

线程池详细内容请[移步](https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/Multithread/java%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93.md)
## 锁
## 乐观锁和悲观锁
悲观锁：假定每次当数据被读取后都有可能修改，所以在使用数据前加锁。比方数据库的行锁和表锁，都是悲观锁，消耗性能较大。
乐观锁：假定每次数据读取都不会修改，所以不上锁。但在更新时，会判断数据是否被更新。判断方法可以用版本号机制或者CAS算法实现。
乐观锁适用于多读的应用类型，这样可以提高吞吐量。
### 乐观锁的版本号机制
就是在设计表时加上版本号，在更新时带上版本号，一同更新。如果更新时查询的版本号跟库里的不一致，说明数据被别人更新过，这次的更新就失败了。
