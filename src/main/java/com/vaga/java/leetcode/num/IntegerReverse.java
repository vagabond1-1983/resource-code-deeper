package com.vaga.java.leetcode.num;

/**
 * @author vaga
 * @version 2020/3/1 11:17 下午
 * @description
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 */
public class IntegerReverse {
        public static int reverse(int x) {
            int reversed = 0;

            while (x != 0) {
                int pop = x % 10;
                x /= 10;
                if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10) && pop > 7) return 0;
                if (reversed < Integer.MIN_VALUE / 10 || (reversed == Integer.MIN_VALUE / 10) && pop < -8) return 0;
                reversed = reversed * 10 + pop;
            }

            return reversed;
        }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
        System.out.println(reverse(123));
    }



}
