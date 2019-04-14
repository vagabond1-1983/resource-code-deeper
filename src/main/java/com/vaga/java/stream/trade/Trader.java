package com.vaga.java.stream.trade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 交易员
 * @Date 2019/4/14 7:27
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trader {
    /**
     * 人名
     */
    private String name;

    /**
     * 城市
     */
    private String city;
}
