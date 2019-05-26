package com.vaga.java.leetcode.sort;

import org.testng.annotations.Test;

/**
 * @Description 简单选择排序
 * @Date 2019/5/26 21:42
 * @Version 1.0
 **/
public class SelectionSort extends BaseDataTest{
    @Test(dataProvider = "random10")
    public void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // 记录下最小值的下标
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                // 找出最小值的下标
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                // 最小值变化，则将最小值跟当前值交换
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
        print(a);
    }
}
