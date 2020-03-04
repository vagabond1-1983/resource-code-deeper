package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/4 4:41 下午
 * @description
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 *
 * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
 *
 *  
 *
 * 示例:
 *
 * 输入: "Hello World"
 * 输出: 5
 */
public class LenOfLastWord {
    @DataProvider(name = "data")
    public static Object[][] data() {
        return new Object[][]{
                {" ", 0},
                {"a", 1},
                {"Hello World", 5},
                {"a ", 1},
                {"    ", 0},
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(String s, int expected) {
        assertThat(lengthOfLastWord(s)).isEqualTo(expected);
        assertThat(leetcodeSolution(s)).isEqualTo(expected);
    }

    public int lengthOfLastWord(String s) {
        if (null == s || s.equals("")) return 0;

        int len = 0;
        char[] chars = s.toCharArray();
        boolean charBefore = false;
        for (int i = chars.length - 1; i >= 0; i-- ) {
            if (chars[i] == ' ') {
                if (charBefore) {
                    return len;
                }
            } else {
                charBefore = true;
                len++;
            }
        }
        return len;
    }

    public int leetcodeSolution(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }

}
