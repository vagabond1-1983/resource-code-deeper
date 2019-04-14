package com.vaga.java.stream.basic;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @Description 流API基础 《Java 8 实战》第四章
 * @Date 2019/4/12 18:09
 * @Version 1.0
 **/
public class StreamAPIBasicSenceTest {
    private static List<Dish> menu;

    @BeforeClass
    public static void init() {
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    /**
     * 找出vegetarian=true的菜品
     */
    @Test
    public void filterTest() {
        List<Dish> vegetarianMenu = menu.stream().
                filter(Dish::isVegetarian).collect(toList());
        assertThat(vegetarianMenu.size()).isEqualTo(4);
    }

    /**
     * 列表偶数输出，不重复
     */
    @Test
    public void distinctTest() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 找出热量超过300的头三道菜
     */
    @Test
    public void limitTest() {
        List<Dish> top3Dishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
        assertThat(top3Dishes)
                .hasSize(3)
                .extracting(Dish::getCalories)
                .contains(800, 700, 400);
    }

    /**
     * 筛选头两个荤菜  P109
     */
    @Test
    public void examFirstTwoMeatsTest() {
        List<Dish> firstTwoMeats = menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
        assertThat(firstTwoMeats).hasSize(2)
                .first()
                .extracting(Dish::getType)
                .isEqualTo(Dish.Type.MEAT);
    }

    /**
     * 输出集合菜品名称长度
     */
    @Test
    public void mapTest() {
        List<Integer> nameLengthList = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        assertThat(nameLengthList).contains(4, 7, 12);
    }

    /**
     * 把单词的字母取出，不重复
     */
    @Test
    public void flatMapTest() {
        String[] words = new String[]{"Hello", "World"};
        List<String> uniqueCharacters = Arrays.stream(words)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        assertThat(uniqueCharacters).hasSize(7);
    }

    /**
     * 给定一个数字列表[1,2,3,4,5]，如何返回一个由每个数的平方构成的列表呢？返回[1,4,9,16,25]
     */
    @Test
    public void examNumberSqrtTest() {
        Integer[] originNumbers = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> sqrtList = Arrays.asList(originNumbers).stream()
                .map(i ->  i * i)
                .collect(toList());
        assertThat(sqrtList).contains(1, 4, 9, 16, 25);
    }

    /**
     * 给定两个数字列表[1,2,3]和[3,4]，返回所有的数对[(1,3),(1,4),(2,3),(2,4),(3,3),(3,4)]
     * 简单起见，可以用有两个元素的数组来代表数对
     */
    @Test
    public void examNumberPairTest() {
        Integer[] firstList = new Integer[]{1, 2, 3};
        Integer[] secondList = new Integer[]{3, 4};
        List<Integer[]> pairs = Arrays.asList(firstList).stream()
                .flatMap(i -> Arrays.asList(secondList).stream()
                .map(j -> new Integer[]{i, j}))
                .collect(toList());
        assertThat(pairs).hasSize(6).contains(new Integer[]{1, 3},
                new Integer[]{1, 4},
                new Integer[]{3, 4});
    }

    /**
     * 扩展前例，只返回总和能被3整除的数对(2,4)和(3,3)
     */
    @Test
    public void examNumberPairDivideThreeTest() {
        List<Integer> firstList = Arrays.asList(1, 2, 3);
        List<Integer> secondList = Arrays.asList(3, 4);
        List<Integer[]> pairsOnlyDivideThree = firstList.stream()
                .flatMap(i -> secondList.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new Integer[]{i, j}))
                .collect(toList());
        assertThat(pairsOnlyDivideThree).hasSize(2)
                .containsOnly(new Integer[]{2, 4}, new Integer[]{3, 3});
    }

    /**
     * 第六章 groupby 分组，根据类型分组菜品
     */
    @Test
    public void groupByTest() {
        Map<Dish.Type, List<Dish>> groupByType = menu.stream()
                .collect(groupingBy(Dish::getType));
        assertThat(groupByType).hasSize(3);
    }
}
