package com.vaga.java.leetcode.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/10 4:35 下午
 * @description
 * 125. 验证回文串
 */
public class PalindromeTest {
    @DataProvider(name = "data")
    public static Object[][] dataset() {
        return new Object[][]{
                {"A man, a plan, a canal: Panama", true},
                {"OP", false},
                {"race a car", false},
                {"", true},
        };
    }

    @Test(dataProvider = "data")
    public void palindrome(String s, boolean expected) {
        assertThat(isPalindrome(s)).isEqualTo(expected);
    }

    public boolean isPalindrome(String s) {
        if (null == s || s.equals("")) return true;
        int left = 0;
        int right = s.length() - 1;
        while ( left < right) {
            if (!isValid(s.charAt(left))) {
                left++;
                continue;
            }
            if (!isValid(s.charAt(right))) {
                right--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean isValid(char c) {
        if ((c >= 0 && c <= 9) ||
                (c >= 'a' && c <= 'z') ||
                (c >= 'A' && c <= 'Z')) {
            return true;
        }
        return false;
    }
}
