package com.vaga.java.leetcode;

import org.testng.annotations.DataProvider;
/**
 * @author vaga
 * @version 2020/3/11 4:03 下午
 * @description basic
 */
public abstract class BaseTest {
    @DataProvider(name = "data")
    public Object[][] dataset() {
        return createData();
    }

    protected abstract Object[][] createData();

}
