package com.vaga.java.leetcode.sort;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Description 数据准备
 * @Date 2019/5/26 15:48
 * @Version 1.0
 **/
public abstract class BaseDataTest {
    private long startTime;
    @BeforeMethod
    public void recordTime() {
        startTime = System.currentTimeMillis();
    }

    @AfterMethod
    public void printPreformance() {
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @DataProvider(name = "random10")
    public Object[][] random10() {
        return generateData(10);
    }

    private Object[][] generateData(int size) {
        return new Object[][]{
                {random(size)},
                {ascArray(size)},
                {descArray(size)}
        };
    }

    private int[] random(int size) {
        int[] notSortedRandomArray = new Random().ints(size, 1, size).toArray();
        return notSortedRandomArray;
    }

    private int[] ascArray(int size) {
        int[] asc = IntStream.rangeClosed(1, size).toArray();
        return asc;
    }

    private int[] descArray(int size) {
        int[] desc = new int[size];
        for (int i = 0, j = size; i < size; i++) {
            desc[i] = j--;
        }
        return desc;
    }

    protected void print(int[] array) {
        Arrays.stream(array)
                .forEach(a -> System.out.print(a + " "));
    }
}
