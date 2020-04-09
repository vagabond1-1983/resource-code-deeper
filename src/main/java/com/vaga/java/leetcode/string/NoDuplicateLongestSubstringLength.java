package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 * 示例1：
 * 输入: "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

    示例2：
    输入: "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

    示例3：
    输入: "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * @Date 2019/6/2 21:36
 * @Version 1.0
 **/
public class NoDuplicateLongestSubstringLength {
    @DataProvider(name = "data")
    public static Object[][] data() {
        return new Object[][]{
                {"abcbcc", 3},
                {"abcabcbb", 3},
                {"bbbbb", 1},
                {"pwwkew", 3}
        };
    }

    /**
     * 观看视频：https://www.youtube.com/watch?v=hw0zHamgaks
     * @param s
     * @param expected
     */
    @Test(dataProvider = "data")
    public void mySolution(String s, int expected) {
        assertThat(lengthOfLongestSubstring(s)).isEqualTo(expected);
    }

    /**
     * 用HashSet保存不重复的字符
     * j代表不重复的起点index
     * res就是结果长度
     * @param s
     * @return
     */
    private int lengthOfLongestSubstring(String s) {
        Set<Character> noDuplicateChars = new HashSet<>();
        int res = 0, i = 0, j = 0;
        while (i < s.length() && j < s.length()) {
            if (!noDuplicateChars.contains(s.charAt(j))) {
                noDuplicateChars.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                noDuplicateChars.remove(s.charAt(i++));
            }
        }
        return res;
    }
}
