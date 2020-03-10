package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/10 3:52 下午
 * @description
 * 167. 两数之和 II - 输入有序数组
 */
public class TwoSumWIthSoredArray {
    @DataProvider(name = "data")
    public static Object[][] dataset() {
        return new Object[][]{
                {new int[]{2, 7, 11, 15}, 9, new int[]{1, 2}},
                {new int[]{2, 7, 11, 15, 20, 30, 34}, 35, new int[]{4, 5}},
        };
    }

    @Test(dataProvider = "data")
    public void twoSumSorted(int[] numbers, int target, int[] expected) {
        int[] ans = twoSumMySolution(numbers, target);
        assertThat(ans).isEqualTo(expected);

        ans = twoSumEnhance(numbers, target);
        assertThat(ans).isEqualTo(expected);
    }

    private int[] twoSumEnhance(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (numbers[start] + numbers[end] != target) {
            if (numbers[start] + numbers[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{start + 1, end + 1};
    }

    //两层循环，非常不好
    public int[] twoSumMySolution(int[] numbers, int target) {
        int n = findPos(numbers, target);
        if (n < 2) return new int[]{1, 2};

        int[] res = new int[2];
        for (int i = n; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] == target - numbers[i]) {
                    res = new int[]{j + 1, i + 1};
                    return res;
                }
            }
        }
        return res;
    }

    private int findPos(int[] numbers, int target) {
        int i = 0;
        while (i < numbers.length && numbers[i] < target) {
            i++;
        }
        return i - 1;
    }
}
