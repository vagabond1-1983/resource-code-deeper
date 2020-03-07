# 源码学习
## 集合源码
### HashMap
Refer: https://github.com/stalary/Source-code-analysis/blob/master/note/HashMap.md
1. 初始大小：初始容量不指定为16，负载因子为0.75
负载因子的作用是计算最大的容量tableSizeFor(capacity * float_number)
默认最大容量是2的30次方
2. hash：采用了k.hash ^ hash >>> 16，高位补1，低位异或
3. put：数组+链表（当链表长度多于8个，转为红黑树）
4. get：链表查找和红黑树查找
5. resize：扩容时为原本容量的两倍
6. java 8在链表长度为8以上时，用红黑树是为了提升查找性能。因为红黑树本质是平衡二叉树(O(logn))，查找次数优于链表(O(n))
### Collections
1. sort(List<T> list) -- ArrayList的排序，其实是转化为数组的排序。数组的排序：useLegacyMergeSort为true则用冒泡排序，否则用TimSort的排序
TimSort：当数组长度小于32时，用二分法排序；当数组长度大于32，则用合并加上二分排序 -- 需要细看
练习：[二分法排序](../../src/main/java/com/vaga/java/sourcecode/jdk/collections/BinarySortExecise.java)
2. binarySearch - 二分查找
3. min、max找最大，最小值
### ArrayList
非线程安全，null不能作为元素
内部是一个Object数组
1. 初始化：可设置capacity
2. add：先扩容，扩大1.5倍，再增加元素
3. remove：数组移动到前一个位置，把最后一个元素位置设置为null，待GC
4. ensureCapacity：当需要一次插入大量元素时，用这个一次性扩容，这样就不用每次1.5倍扩容了。提升性能。
### Arrays
1. sort：用DualPivot改进的经典快排。经典快排是将集合分成两堆然后递归。DualPivot是分成三堆。-- 具体还需要细看
## String
### StringBuilder
## 多线程源码
### [多线程理论](../concurrent) 
### synchronized
### CompletableFuture
### ArrayBlockingQueue
### ConcurrentHashMap
### CountDownLatch
### CyclicBarrier

## TODO
### 排序
1. TimSort
2. DualPivot
3. 经典快排