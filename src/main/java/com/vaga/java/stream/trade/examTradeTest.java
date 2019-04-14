package com.vaga.java.stream.trade;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 关于交易的测验 《Java 8 实战》 5.5.1
 * @Date 2019/4/14 7:30
 * @Version 1.0
 **/
public class examTradeTest {
    private List<Transaction> transactions;

    @Before
    public void init() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brain = new Trader("Brain", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brain, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序（从低到高）
     */
    @Test
    public void findTransactionOn2011AndOrderASCTest() {
        List<Transaction> transactionsOn2011AndOrdered = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        assertThat(transactionsOn2011AndOrdered)
                .hasSize(2)
                .extracting(Transaction::getYear)
                .containsOnly(2011);
        assertThat(transactionsOn2011AndOrdered)
                .extracting(Transaction::getValue)
                .containsExactlyInAnyOrder(400, 300);
    }

    /**
     * 交易员都在哪些不同的城市工作过
     */
    @Test
    public void cityListTest() {
        List<String> cities = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        assertThat(cities).hasSize(2).containsOnly("Cambridge", "Milan");

        // 书中改进======================================
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(toSet());
    }

    /**
     * 查找所有来自于Cambridge的交易员，并按姓名排序
     */
    @Test
    public void traderFromCambridgeWithAlphabeteOrderTest() {
        List<Trader> tradersFromCambridge = transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .map(Transaction::getTrader)
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        assertThat(tradersFromCambridge).hasSize(3)
                .extracting(Trader::getName)
                .containsExactlyInAnyOrder("Alan", "Brain", "Raoul");
    }

    /**
     * 返回所有交易员的姓名，按字母顺序排序
     * 应该返回姓名字符串
     */
    @Test
    public void traderNamesWithAlphaOrderTest() {
        List<String> traderNames = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(toList());
        assertThat(traderNames).containsExactlyInAnyOrder("Alan", "Brain", "Mario", "Raoul");
    }

    /**
     * 返回所有交易员的姓名字符串，按字母顺序排序--书中答案
     */
    @Test
    public void traderNamesStringWithAlphaOrderTest() {
        String tradersString = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(","));
        assertThat(tradersString).isEqualTo("Alan,Brain,Mario,Raoul");
    }

    /**
     * 有没有交易员是在Milan工作的
     */
    @Test
    public void traderWorkInMilanTest() {
        Optional<Trader> traderWorkInMilan = transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"))
                .map(t -> t.getTrader())
                .findAny();
        assertThat(traderWorkInMilan.isPresent()).isEqualTo(true);
        assertThat(traderWorkInMilan.get()).extracting(Trader::getName).isEqualTo("Mario");
    }

    /**
     * 打印生活在Cambridge的交易员的所有交易额
     */
    @Test
    public void tradeTotalValueInCambridgeTest() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
                .forEach(t -> System.out.println(t.getValue()));
    }

    /**
     * 所有交易中，最高的交易额是多少
     * 看了参考答案，对于reduce的用法还是不熟
     */
    @Test
    public void maxValueTest() {
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        assertThat(maxValue.isPresent());
        assertThat(maxValue.get()).isEqualTo(1000);
    }

    /**
     * 找到交易额最小的交易
     * 看了参考答案，对于reduce的用法还是不熟
     */
    @Test
    public void transactionWithMinValueTest() {
        Optional<Transaction> transaction = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() > t2.getValue() ? t2 : t1);
        assertThat(transaction.get()).extracting(Transaction::getValue).isEqualTo(300);

    }
}
