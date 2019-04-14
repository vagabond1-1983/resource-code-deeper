package com.vaga.java.stream.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description Dish
 * @Date 2019/4/3 13:32
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public enum Type {MEAT, FISH, OTHER}
}
