package com.vaga.java.leetcode.binarySearch;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 合并区间
 * 给定区间集合，合并有重合的区间
 * 示例1：
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * 示例2：
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * @Date 2019/5/5 9:17
 * @Version 1.0
 **/
public class MergeIntervals {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}, new int[][]{{1, 6}, {8, 10}, {15, 18}}},
                {new int[][]{{1, 4}, {4, 5}}, new int[][]{{1, 5}}}

        };
    }

    @Test(dataProvider = "data")
    public void theirSolution(int[][] input, int[][] expected) {
        int[][] actual = null;
        if (input.length < 2) {
            actual = input;
        } else {
            List<int[]> actualList = new ArrayList<>();
            int[] currentArray = input[0];
            for (int i = 1; i < input.length; i++) {
                if (currentArray[1] >= input[i][0]) {
                    currentArray[1] = input[i][1];
                } else {
                    actualList.add(currentArray);
                    currentArray = input[i];
                }
            }
            actualList.add(currentArray);
            actual = toArray(actualList);
        }
        assertThat(actual.length).isEqualTo(expected.length);
        assertThat(actual).containsOnly(expected);
    }

    private int[][] toArray(List<int[]> actualList) {
        int[][] array = new int[actualList.size()][];
        for (int i = 0; i < actualList.size(); i++) {
            array[i] = actualList.get(i);
        }
        return array;
    }

}
