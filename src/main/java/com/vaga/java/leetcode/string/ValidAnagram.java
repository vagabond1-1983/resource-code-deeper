package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description
 * 判断一个字符串是否是字母异位
    示例 1:
    输入：s="anagram",t="nagaram"
    输出：true
    示例 2:
    输入：s="rat",t="car"
    输出：false
 * @Date 2019/4/23 6:12
 * @Version 1.0
 **/
public class ValidAnagram {
    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][] {
                {"anagram", "nagaram", true},
                {"rat", "car", false}
        };
    }

    @Test(dataProvider = "data")
    public void theirSolution(String s, String t, boolean expected) {
        char[] sortedS = s.toCharArray();
        char[] sortedT = t.toCharArray();
        Arrays.sort(sortedS);
        Arrays.sort(sortedT);
        boolean actual = String.valueOf(sortedS).equalsIgnoreCase(String.valueOf(sortedT));
        assertThat(actual).isEqualTo(expected);
    }
}
