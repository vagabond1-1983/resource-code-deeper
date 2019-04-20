package com.vaga.java.concurrent.singleton;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 单例测试
 * @Date 2019/4/20 21:31
 * @Version 1.0
 **/
public class SingletonTest {
    private List<ISingleton> list = new ArrayList<>(1000);
    private List<ISingleton> enumList = new ArrayList<>(1000);

    @Test(invocationCount = 1000)
    public void syncSingletonTest() {
        ISingleton s = SyncSingleton.getInstance();
        list.add(s);
    }

    @Test(invocationCount = 1000)
    public void enumSingletonTest() {
        enumList.add(EnumSingleton.INSTANCE);
    }

    @AfterTest
    public void checkObject() {
        assertThat(list).containsOnly(SyncSingleton.getInstance());
        assertThat(enumList).containsOnly(EnumSingleton.INSTANCE);
    }
}
