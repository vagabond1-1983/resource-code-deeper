package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/5 3:03 下午
 * @description 67.二进制求和
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 *
 * 输入为非空字符串且只包含数字 1 和 0。
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 */
public class BinarySum {
    @DataProvider(name = "data")
    public static Object[][] dataset() {
        return new Object[][]{
                {"11", "1", "100"},
                {"100", "1010", "1110"},
                {"1","1111","10000"},
        };
    }

    @Test(dataProvider = "data")
    public void test(String a, String b, String expected) {
        assertThat(mySolution(a, b)).isEqualTo(expected);
    }

    private String mySolution(String a, String b) {
        StringBuilder result = new StringBuilder();
        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int i = Math.max(aIndex, bIndex);
        int plus = 0;
        for ( ; i >= 0; i--) {
            int aVal = 0;
            if (aIndex >= 0) {
                aVal = Integer.parseInt(a.charAt(aIndex--)+"");
            }
            int bVal = 0;
            if (bIndex >= 0) {
                bVal = Integer.parseInt(b.charAt(bIndex--)+"");
            }

            if (aVal == 1 && bVal == 1) {
                result.append(plus);
                plus = 1;
            } else {
                int sum = aVal + bVal + plus;
                if (sum > 1) {
                    result.append(0);
                    plus = 1;
                } else {
                    result.append(sum);
                    plus = 0;
                }
            }
        }

        if (plus == 1) {
            result.append(1);
        }

        return result.reverse().toString();
    }
}
