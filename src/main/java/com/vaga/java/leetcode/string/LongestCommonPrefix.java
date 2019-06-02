package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。
    示例 1:
    输入: ["flower","flow","flight"]
    输出: "fl"
    示例 2:
    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
    说明: 所有输入只包含小写字母 a-z 。
 * @Date 2019/4/21 21:04
 * @Version 1.0
 **/
public class LongestCommonPrefix {
    @DataProvider(name = "data")
    public static Object[][] data() {
        return new Object[][]{
                {"fl", Arrays.asList("flower", "flow", "flight")},
                {"", Arrays.asList("dog", "racecar", "ca")}
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(String expected, List<String> strs) {
        int count = strs.size();
        String actualPrefix = "";
        for (int i = 0; i < count; i++) {
            char compareChar = strs.get(0).charAt(i);
            for (int j = 1; j < strs.size(); j++) {
                // 问题在于：每个元素都进行了比较，即使不一样了，也没有结束循环
                if (strs.get(j).charAt(i) == compareChar || i == strs.get(j).length()) {
                    actualPrefix = strs.get(0).substring(0, i);
                    break;
                }
            }
        }
        assertThat(actualPrefix).isEqualTo(expected);
    }

    @Test(dataProvider = "data")
    public void mySolutionEnhancement(String expected, List<String> strs) {
        int count = strs.size();
        String actualPrefix = "";
        boolean needCompare = true;
        for (int i = 0; i < count; i++) {
            if (!needCompare) {
                break;
            }
            
            char compareChar = strs.get(0).charAt(i);
            for (int j = 1; j < strs.size(); j++) {
                // 当前字符相同或者长度未越界则最长前缀增加
                if (strs.get(j).charAt(i) == compareChar && i < strs.get(j).length()) {
                    actualPrefix = strs.get(0).substring(0, i);
                    break;
                } else {
                    // 当前字符不同，则不再比较
                    needCompare = false;
                }
            }
        }
        assertThat(actualPrefix).isEqualTo(expected);
    }

    /**
     * 使用二分查找找到最长前缀
     * 1. 找到最小长度的字符串 - 为获取前缀的字符串
     * 2. 将此字符串一分为二
     * 3. 前面是否是所有字符串的前缀
     * 4. 后面是否是所有字符串的前缀
     * @param expected
     * @param strs
     */
    @Test(dataProvider = "data")
    public void binarySearchSolutionTest(String expected, List<String> strs) {
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            minLength = Math.min(minLength, s.length());
        }

        int low = 0;
        int high = minLength;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (isCommonPrefix(strs, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        String actual = strs.get(0).substring(0, (low + high) / 2);
        assertThat(actual).isEqualTo(expected);
    }

    private boolean isCommonPrefix(List<String> strs, int length) {
        String prefix = strs.get(0).substring(0, length);
        for (String i : strs) {
            if (!i.substring(0, length).equalsIgnoreCase(prefix)) {
                return false;
            }
        }
        return true;
    }
}
