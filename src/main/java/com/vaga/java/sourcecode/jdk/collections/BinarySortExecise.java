package com.vaga.java.sourcecode.jdk.collections;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 二分法排序
 * 给定一个数组，用二分法排序
 * e.g. [2,5,3,67,17,98]
 * 输出：[2,3,5,17,67,98]
 * @Date 2019/5/12 21:02
 * @Version 1.0
 **/
public class BinarySortExecise {
    @DataProvider(name = "data")
    public Object[][] testData() {
        return new Object[][]{
                {new int[]{2, 5, 3, 67, 17, 98}, new int[]{2, 3, 5, 17, 67, 98}}
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(int[] input, int[] expected) {
        int[] actual = new int[input.length];
        actual[0] = input[0];
        for (int i = 1; i < input.length; i++) {
            int low = 0;
            int high = i;
            while (low < high) {
                int middle = (high + low) / 2;
                if (actual[middle] > input[i]) {
                    high = middle;
                } else {
                    low = middle + 1;
                }
            }
            int n = i - low;
            switch (n) {
                case 2:
                    actual[low + 2] = actual[low + 1];
                case 1:
                    actual[low + 1] = actual[low];
                    break;
                default:
                    System.arraycopy(actual, low, actual, low + 1, n);
            }
            actual[low] = input[i];
        }
        assertThat(actual).as("二分排序结果错误").containsExactly(expected);
    }
}
