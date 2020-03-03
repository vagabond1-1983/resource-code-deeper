package com.vaga.java.leetcode.stack;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Map;
import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 有效的括号
 * 示例 1:
    输入: "()"
    输出: true
    示例 2:
    输入: "()[]{}"
    输出: true
    示例 3:
    输入: "(]"
    输出: false
    示例 4:
    输入: "([)]"
    输出: false
    示例 5:
    输入: "{[]}"
    输出: true
 * @Date 2019/4/30 11:10
 * @Version 1.0
 **/
public class ValidParentheses {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {"()", true},
                {"()[]{}", true},
                {"(]", false},
                {"([)]", false},
                {"{[]}", true},
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(String input, boolean expected) {
        Stack<Character> little = new Stack<>();
        Stack<Character> middle = new Stack<>();
        Stack<Character> large = new Stack<>();
        boolean actual = true;
        for (char i : input.toCharArray()) {
            if (!actual) break;
            switch (i) {
                case '(':
                    if (little.empty()) {
                        little.push(i);
                    } else {
                        actual = false;
                    }
                    break;
                case ')':
                    if (little.empty() || !little.pop().equals('(')) {
                        actual = false;
                    }
                    break;
                case '[':
                    if (middle.empty()) {
                        middle.push(i);
                    } else {
                        actual = false;
                    }
                    break;
                case ']':
                    if (middle.empty() || !middle.pop().equals('[')) {
                        actual = false;
                    }
                    break;
                case '{':
                    if (large.empty()) {
                        large.push(i);
                    } else {
                        actual = false;
                    }
                    break;
                case '}':
                    if (large.empty() || !large.pop().equals('{')) {
                        actual = false;
                    }
                    break;
            }
        }
        assertThat(actual).as("括号检查结果不同").isEqualTo(expected);
    }

    @Test(dataProvider = "data")
    public void my0303Rewrite(String input, boolean expected) {
        char[] chars = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char i : chars) {
            if (stack.empty() || i == '(' || i == '[' || i == '{') {
                stack.push(i);
                break;
            }

            switch (i) {
                case ')':
                    if (stack.pop() != '(') assertThat(expected).isFalse();
                    break;
                case ']':
                    if (stack.pop() != '[') assertThat(expected).isFalse();
                    break;
                case '}':
                    if (stack.pop() != '{') assertThat(expected).isFalse();
                    break;
            }
        }
        if (stack.empty()) {
            assertThat(expected).isTrue();
        } else {
            assertThat(expected).isFalse();
        }

    }

    @Test(dataProvider = "data")
    public void theirSolution(String input, boolean expected) {
        Map<Character, Character> pair = Maps.newHashMap();
        pair.put(')', '(');
        pair.put(']', '[');
        pair.put('}', '{');

        Stack<Character> stack = new Stack<>();
        boolean actual = true;
        for (Character c : input.toCharArray()) {
            if (!actual) break;
            if (pair.containsKey(c)) {
                char topElement = stack.empty() ? 'a' : stack.pop();

                if (topElement != pair.get(c)) {
                    actual = false;
                    break;
                }
            } else {
                stack.push(c);
            }
        }

        assertThat(actual).isEqualTo(expected);
    }
}
