package com.vaga.java.leetcode.sort;

import com.vaga.java.leetcode.BaseTest;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/17 5:31 下午
 * @description 归并排序
 * @link https://juejin.im/post/5ab4c7566fb9a028cb2d9126
 */
public class MergeSort extends BaseTest {

    @Override
    protected Object[][] createData() {

        return new Object[][]{
                {new int[]{9, 2, 5, 1, 3, 2, 9, 5, 2, 1, 8}}
        };
    }

    @Test(dataProvider = "data")
    public void mergesortTest(int[] arrays) {
        int[] expected = Arrays.copyOf(arrays, arrays.length);
        Arrays.sort(expected);
        mergeSort(arrays);
        assertThat(arrays).containsExactly(expected);
    }

    private void mergeSort(int[] arrays) {
        if (arrays.length < 1) return;

        mergeSortRange(arrays, 0, arrays.length - 1);
    }

    private void mergeSortRange(int[] arrays, int lo, int hi) {
        if (lo == hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        mergeSortRange(arrays, lo, mid);
        mergeSortRange(arrays, mid + 1, hi);
        merge(arrays, lo, mid + 1, hi);
    }

    private void merge(int[] arrays, int lo, int mid, int hi) {
        int[] left = new int[mid - lo];
        int[] right = new int[hi - mid + 1];
        for (int i = lo; i < mid; i++) {
            left[i - lo] = arrays[i];
        }
        for (int i = mid; i <= hi; i++) {
            right[i - mid] = arrays[i];
        }

        int i = 0, j = 0, k = lo;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                arrays[k] = left[i];
                i++;
                k++;
            } else {
                arrays[k] = right[j];
                j++;
                k++;
            }
        }

        // no moved elements
        while (i < left.length) {
            arrays[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            arrays[k] = right[j];
            j++;
            k++;
        }
    }
}
