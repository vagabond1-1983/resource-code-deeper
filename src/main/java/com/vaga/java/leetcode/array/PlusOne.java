package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/4 5:28 下午
 * @description
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class PlusOne {
    @DataProvider(name = "data")
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{1,2,3}, new int[]{1, 2, 4}},
                {new int[]{9}, new int[]{1,0}},
                {new int[]{9, 9}, new int[]{1, 0, 0}},
        };
    }

    @Test(dataProvider = "data")
    public void run(int[] input, int[] expected) {
        assertThat(mySolution(input)).isEqualTo(expected);
    }

    private int[] mySolution(int[] digits) {
        int len = digits.length - 1;
        int plusOne = 1;
        while (len >= 0) {
            int result = digits[len] + plusOne;
            if (result == 10) {
                // 发生进位
                digits[len] = 0;
                len--;
            } else {
                digits[len] = result;
                return digits;
            }
        }
        // 第一位是否有进位
//        if (digits[0] == 0) {
//            int[] newDigits = new int[digits.length + 1];
//            System.arraycopy(digits, 0, newDigits, 1, digits.length);
//            newDigits[0] = 1;
//            return newDigits;
//        }
        if (digits[0] == 0) {
            digits = new int[digits.length + 1];
            digits[0] = 1;
        }
        return digits;
    }
}
