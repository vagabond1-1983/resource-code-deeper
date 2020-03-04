package com.vaga.java.basic;

import java.util.HashMap;
import java.util.Objects;

public class EqualsAndHashCodeRewrite {
    Integer a;
    public EqualsAndHashCodeRewrite(Integer a) {
        this.a = a;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqualsAndHashCodeRewrite that = (EqualsAndHashCodeRewrite) o;
        return Objects.equals(a, that.a);
    }

    // 为什么只重写equals，没有重写hashcode，添加到hashmap后，虽然是相同的值，但是会变成两个元素
    public static void main(String[] args) {
        EqualsAndHashCodeRewrite key1 = new EqualsAndHashCodeRewrite(1);
        EqualsAndHashCodeRewrite key2 = new EqualsAndHashCodeRewrite(1);
        System.out.println("key1的hashCode为"+ key1 +"key2的hashCode为" + key2);
        System.out.println("key1.equals(key2)的结果为"+(key1.equals(key2)));

        HashMap<EqualsAndHashCodeRewrite,String> map = new HashMap<EqualsAndHashCodeRewrite,String>();
        map.put(key1,"value1");
        map.put(key2,"value2");
        System.out.println("HashMap是"+map.toString());
        }
}