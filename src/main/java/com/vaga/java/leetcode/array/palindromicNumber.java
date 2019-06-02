package com.vaga.java.leetcode.array;

import com.vaga.java.common.annotation.Easy;
import com.vaga.java.common.annotation.MyAnswer;
import com.vaga.java.common.annotation.Standard;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 判断一个给定的数字是否是回文数字
                e.g. 646 是
                e.g. 456 否
 * @Date 2019/4/19 10:41
 * @Version 1.0
 **/
public class palindromicNumber {
    @DataProvider
    public static Object[][] dataset() {
        return new Object[][]{
                {464, true},
                {-464, false},
                {453, false}
        };
    }

    @MyAnswer @Easy
    @Test
    public void palindromicNumberTest(Integer input, boolean expected) {
        char[] testCharList = Integer.toString(input).toCharArray();
        boolean actual = true;
        for (int i = 0; i < testCharList.length; i++) {
            if (testCharList[i] == testCharList[testCharList.length - i - 1]) {
                continue;
            } else {
                actual = false;
            }
        }
        assertThat(actual).isEqualTo(expected);
    }

    @Standard
    @Test
    public void palindromicNumberFromSolution2Test(Integer input, boolean expected) {
        boolean actual = true;
        if (input <= 0) {
            actual = false;
        }

        int x = input;
        if (actual) {
            int reversed = 0;
            while (x > 0) {
                int digit = x % 10;
                reversed *= 10;
                reversed += digit;
                x = x / 10;
            }
            actual = input == reversed ? true : false;
        }
        assertThat(actual).isEqualTo(expected);
    }
}
