package com.vaga.java.leetcode.sort;

import org.testng.annotations.Test;

/**
 * @Description 插入排序
 * @Date 2019/5/26 16:14
 * @Version 1.0
 **/
public class InsertionSort extends BaseDataTest {
    @Test(dataProvider = "random10")
    public void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 待比较的元素
            int temp = array[i];
            for (int j = i; j >= 0; --j) {
                // 前面的值比当前值大，则依次交换前面的值往后挪
                if (j > 0 && array[j - 1] > temp) {
                    array[j] = array[j - 1];
                } else {
                    // j已经是最前面，或者前面的值小于当前值，则把当前值挪动到j位置
                    array[j] = temp;
                    break;
                }
            }
        }
        print(array);
    }
}
