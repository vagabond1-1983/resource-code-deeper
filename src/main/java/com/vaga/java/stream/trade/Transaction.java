package com.vaga.java.stream.trade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 交易
 * @Date 2019/4/14 7:28
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    /**
     * 交易员
     */
    private Trader trader;

    /**
     * 交易年份
     */
    private int year;

    /**
     * 交易值
     */
    private int value;
}
