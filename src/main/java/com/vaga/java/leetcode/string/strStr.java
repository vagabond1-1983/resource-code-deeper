package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author vaga
 * @version 2020/3/3 5:00 下午
 * @description 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class strStr {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"hello", "ll", 2},
                {"aaaaa", "bba", -1},
                {"", "", 0},
                {"", "a", -1},
                {"a", "a", 0}
        };
    }

    int strStr(String haystack, String needle) {
        if (null == haystack || null == needle) return -1;

        if (haystack.equals("") && !needle.equals("")) {
            return  -1;
        } else if (needle.equals("")){
            return  0;
        }

        char[] target = haystack.toCharArray();
        char[] compare = needle.toCharArray();
        int pos = -1;
        int j = 0;
        for (int i = 0; i < target.length; i++) {
            // 比较字符是否一致
            if (target[i] != compare[j]) {
                // 重置
                j = 0;
            } else {
                // 匹配上needle字符串，退出循环
                if (j >= compare.length - 1) {
                    pos = i - j;
                    break;
                }
                j++;
            }
        }
        return pos;
    }

    @Test(dataProvider = "data")
    public void strStrMySolution(String haystack, String needle, Integer expected) {
        assert strStr(haystack, needle) == expected;

    }
}
