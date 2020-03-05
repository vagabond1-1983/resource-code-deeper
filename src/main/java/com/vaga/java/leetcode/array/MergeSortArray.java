package com.vaga.java.leetcode.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/5 4:18 下午
 * @description 88.合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class MergeSortArray {
    @DataProvider(name = "data")
    public static Object[][] dataset() {
        return new Object[][]{
                {new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{2, 5, 6}, 3, new int[]{1, 2, 2, 3, 5, 6}},
                {new int[]{1}, 1, new int[]{}, 0, new int[]{1}},
                {new int[]{0, 0, 0}, 0, new int[]{1, 2}, 2, new int[]{1, 2}},
                {new int[]{0}, 0, new int[]{1}, 1, new int[]{1}},
        };
    }

    @Test(dataProvider = "data")
    public void test(int[] num1, int m, int[] num2, int n, int[] expected) {
        assertThat(mySolution(num1, m, num2, n)).isEqualTo(expected);
    }

    public int[] mySolution(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        if (m == 0) {
            nums1 = nums2;
            return nums1;
        } else if (n == 0) {
            return nums1;
        }

        for (int i = 0 ; i < m; i++ ) {
            if (nums1[i] > nums2[j]) {
                System.arraycopy(nums1, i, nums1, i+1, m - i);
                nums1[i] = nums2[j];
                j++;
            }
        }

        if (j <= n) {
            System.arraycopy(nums2, j, nums1,  m + j, n -j);
        }
        return nums1;
    }
}
