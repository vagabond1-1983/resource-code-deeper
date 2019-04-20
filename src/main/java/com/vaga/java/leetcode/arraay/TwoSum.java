package com.vaga.java.leetcode.arraay;

import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 两数之和
 * @Date 2019/4/20 22:25
 * @Version 1.0
 **/
public class TwoSum {
    private int[] data = new int[]{2, 7, 11, 15};
    private int target = 9;

    @Test
    public void mySolutionTest() {
        Map<Integer, Integer> sourceMap = new HashMap<>();
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            sourceMap.put(data[i], i);
        }

        int[] result = new int[2];
        for (int i = 0; i < data.length; i++) {
            int value = target - data[i];
            if (null != sourceMap.get(value)) {
                result[0] = i;
                result[1] = sourceMap.get(value);
            }
        }
        assertThat(result).contains(0, 1);
    }

    @Test
    public void leetcodeSolutionTest() {
        Map<Integer, Integer> munisMap = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < data.length; i++) {
            int munisValue = target - data[i];
            if (munisMap.containsKey(munisValue)) {
                result[0] = i;
                result[1] = munisMap.get(munisValue);
                break;
            }
            munisMap.put(data[i], i);
        }
        assertThat(result).contains(0, 1);
    }
}
