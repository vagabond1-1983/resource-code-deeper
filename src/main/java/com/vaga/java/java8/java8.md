# Java8
## stream API
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
## CompletableFuture
异步框架
返回Future对象，通过get获取结果
主要使用CompletableFuture.supplyAsync进行异步操作。
高级用法中有thenComplete，thenApply等