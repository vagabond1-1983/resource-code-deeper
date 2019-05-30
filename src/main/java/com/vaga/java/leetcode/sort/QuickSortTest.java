package com.vaga.java.leetcode.sort;

import org.testng.annotations.Test;

public class QuickSortTest extends BaseDataTest {
    @Test(dataProvider = "random10")
    public void quickSortTest(int[] array) {
        _quickSort(array, 0, array.length -1);
        print(array);
    }

    private void _quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        } else {
            int mid = findMiddle(array, start, end);
            _quickSort(array, start, mid - 1);
            _quickSort(array, mid + 1, end);
        }
    }

    private int findMiddle(int[] array, int start, int end) {
        int pivot = array[end];
        int left = start;
        int right = end - 1;
        while (true) {
            while (left < end && array[left] <= pivot) {left++;}

            while (right > start && array[right] >= pivot) {right--;}

            // left, right是否交叉
            if (left >= right) {
                // 交叉则交换left和pivot
                int temp = array[left];
                array[left] = pivot;
                array[end] = temp;
            } else {
                // 交换left和right
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
            return left;
        }
    }
}
