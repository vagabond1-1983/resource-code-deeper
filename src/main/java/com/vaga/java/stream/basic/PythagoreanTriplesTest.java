package com.vaga.java.stream.basic;

import org.junit.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description 此代码对应 5.6.3 数值流应用：勾股数
 * @Date 2019/4/14 16:36
 * @Version 1.0
 **/
public class PythagoreanTriplesTest {
    /**
     * 找出1-100能构成勾股数的三元组
     */
    @Test
    public void triplesTest() {
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                        .filter(t -> t[2] % 1 == 0)
                );
        pythagoreanTriples
                .limit(5)
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

    }
}
