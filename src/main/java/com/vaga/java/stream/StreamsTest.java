package com.vaga.java.stream;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;


/**
 * @Description 流API测试
 * @Author kongxiangyun
 * @Date 2019/4/12 18:09
 * @Version 1.0
 **/
public class StreamsTest {
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

    @Test
    public void filterTest() {
        List<Dish> vegetarianMenu = menu.stream().
                filter(Dish::isVegetarian).collect(toList());
        assertThat(vegetarianMenu.size()).isEqualTo(4);
    }

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
     * 筛选头两个荤菜
     */
    @Test
    public void exciseFirstTwoMeatsTest() {
        List<Dish> firstTwoMeats = menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());
        assertThat(firstTwoMeats).hasSize(2)
                .first()
                .extracting(Dish::getType)
                .isEqualTo(Dish.Type.MEAT);
    }

}
