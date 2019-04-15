package com.vaga.java.stream.parallel;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 单词统计，讨论并行流
 * @Date 2019/4/15 14:58
 * @Version 1.0
 **/
public class wordCountTest {
    private String article;

    @Before
    public void init() {
        article = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
    }

    /**
     * 根据空格后是字母的条件，判断单词个数
     */
    @Test
    public void genericWayTest() {
        int count = 0;
        boolean lastSpace = true;
        for (char c : article.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    count++;
                }
                lastSpace = false;
            }
        }
        System.out.println(count);
    }

    /**
     * 用WordCounter流方式处理
     */
    @Test
    public void streamTest() {
        WordCounter sumWordCounter = article.chars()
                .mapToObj(c -> (char) c)
                .reduce(new WordCounter(0, false),
                        WordCounter::accumulate,
                        WordCounter::combine);
        assertThat(sumWordCounter).extracting(WordCounter::getCounter)
                .isEqualTo(19);
    }
}
