package com.vaga.java.leetcode.arraay;

import com.vaga.java.common.annotation.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 判断一个给定的数字是否是回文数字
                e.g. 646 是
                e.g. 456 否
 * @Date 2019/4/19 10:41
 * @Version 1.0
 **/
@RunWith(Parameterized.class)
public class palindromicNumber {
    @Parameterized.Parameters(name = "{index}: palindromic({0})={1}")
    public static Iterable<Object[]> dataset() {
        return Arrays.asList(new Object[][]{
                {464, true},
                {-464, false},
                {453, false}
        });
    }

    public int input;

    public boolean expected;

    public palindromicNumber(int input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @MyAnswer @Easy
    @Test
    public void palindromicNumberTest() {
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
    public void palindromicNumberFromSolution2Test() {
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
