package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 27.删除元素
 * 示例 1：
    Given nums = [3,2,2,3], val = 3
    return length = 2
    3被移除，剩下2有2个
    示例 2：
    Given nums = [0,1,2,2,3,0,4,2], val = 2
    return length = 5
    2被移除，剩下[0,1,3,0,4]
 * @Date 2019/4/24 22:46
 * @Version 1.0
 **/
public class RemoveElement {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return new Object[][]{
                {new int[]{3, 2, 2, 3}, 3, 2, new int[]{2, 2}},
                {new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5, new int[]{0, 1, 3, 0, 4}}
        };
    }

    @Test(dataProvider = "data")
    public void mySolution(int[] array, int removedElement, int expectedLength, int[] expectedArray) {
        int actualLength = array.length;
        for (int i : array) {
            if (i == removedElement) {
                actualLength--;
            }
        }
        assertThat(actualLength).isEqualTo(expectedLength);
    }

    @Test(dataProvider = "data")
    public void theirSolution(int[] array, int removedElement, int expectedLength, int[] expectedArray) {
        int i = 0;
        int n = array.length;
        while (i < n) {
            if (array[i] == removedElement) {
                array[i] = array[n - 1];
                n--;
            } else {
                i++;
            }
        }
        int[] actualArray = new int[n];
        System.arraycopy(array, 0, actualArray, 0, n);
        assertThat(actualArray).hasSize(expectedLength).containsExactlyInAnyOrder(expectedArray);

    }
}
