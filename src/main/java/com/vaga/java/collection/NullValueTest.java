package com.vaga.java.collection;

import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vaga
 * @version 2020/3/6 11:23 上午
 * @description set，list，map对空值的表现
 */
public class NullValueTest {
    private Set<String> set = new HashSet<>();
    private List<String> list = new ArrayList<>();
    private Map<String, String> map = new HashMap<>();

    @Test
    public void nullTest() {
        set.add("a");
        set.add(null);
        assertThat(set)
                .describedAs("set元素可以是null")
                .containsNull()
                .size().isEqualTo(2);

        list.add(null);
        list.add("a");
        assertThat(list)
                .describedAs("list元素可以是null")
                .containsNull()
                .size().isEqualTo(2);

        map.put(null, null);
        map.put(null, "sss");
        assertThat(map)
                .describedAs("map的key和value都可以是null")
                .containsKey(null)
                .size().isEqualTo(1);
        assertThat(map.get(null)).isEqualTo("sss");
    }

    @Test
    public void duplicateTest() {
        set.add("a");
        set.add("a");
        assertThat(set)
                .describedAs("set不允许重复元素")
                .size().isEqualTo(1);

        list.add("a");
        list.add("a");
        assertThat(list)
                .describedAs("list允许重复元素")
                .containsOnly("a")
                .size().isEqualTo(2);

        map.put("s", "sss");
        map.put("s", "aaa");
        assertThat(map)
                .describedAs("map的key不能重复")
                .containsKey("s")
                .size().isEqualTo(1);

    }


}
