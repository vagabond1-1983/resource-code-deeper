package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2019/7/18 10:46 PM
 * @description 最接近三数之和
 */
public class ThreeSumClosest {

    @DataProvider(name = "data")
    public static Object[][] dataset() {
        return new Object[][]{
                {new int[]{0, 0, 0}, 1, 0},
                {new int[]{0, 2, 1, -3}, 1, 0}
        };
    }

    @Test(dataProvider = "data")
    public void threeSumClosestTest(int[] nums, int target, int expected) {
        int ans = mySolution(nums, target);
        assertThat(ans).isEqualTo(expected);
    }

    public int mySolution(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int min = 0;
        int lastMin = Integer.MAX_VALUE;
        while (i + 1 < j) {
            min = target - (nums[i] + nums[i+1] + nums[j]);
            if (min > 0) {
                // target is larger, i++
                i++;
            } else if (min < 0) {
                // target is smaller, j--
                j--;
            } else {
                // min == 0, get the target
                return target;
            }
            // 找出最小的差值
            min = Math.min(lastMin, min);
            // 保存上一次min
            lastMin = min;
        }
        return target - min;
    }
}
