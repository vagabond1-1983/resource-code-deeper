package com.vaga.java.leetcode.binarySearch;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 两数除法，不能使用除法、取模等数学符号
 * 示例 1:
    输入：dividend = 10, divisor = 3
    输出：3
    示例 2：
    输入：dividend = 7, divisor = -3
    输出：-2
 * @Date 2019/4/23 21:22
 * @Version 1.0
 **/
public class DivideTwoInteger {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {10, 3, 3},
                {7, -3, -2}
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(Integer dividend, Integer divisor, Integer expected) {
        List<Integer> numbers = IntStream.rangeClosed(1, dividend)
                            .boxed()
                            .collect(toList());

        int low = 1;
        int high = numbers.size();
        while (low <= high) {
            int mid = (high + low) / 2;
            if (numbers.get(mid) >= Math.abs(divisor)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        Integer actual = numbers.get((high + low) / 2);

        if (dividend * divisor < 0) {
            actual = actual * -1;
        }

        assertThat(actual).isEqualTo(expected);
    }
}
