package com.vaga.java.leetcode.array;

import com.vaga.java.leetcode.BaseTest;
import org.assertj.core.util.Lists;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author vaga
 * @version 2020/3/11 4:01 下午
 * @description 119. 杨辉三角II
 */
public class TriangleSec extends BaseTest {

    @Override
    protected Object[][] createData() {
        return new Object[][]{
                {3, Lists.list(1,3,3,1)},
        };
    }

    @Test(dataProvider = "data")
    public void triangle(int rowIndex, List<Integer> expected) {
        assertThat(getRow(rowIndex)).isEqualTo(expected);
    }

    private List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        if (rowIndex == 1) return row;

        for (int i = 1; i <= rowIndex; i++) {
            Integer pre = row.get(0);
            for (int j = 1; j < i; j++) {
                Integer tmp = pre;
                pre = row.get(j);
                row.set(j, pre + tmp);
            }
            row.add(1);
        }
        return row;
    }
}
