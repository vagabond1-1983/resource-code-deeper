package com.vaga.java.java8.stream.parallel;

import lombok.Data;

/**
 * @Description 单词计数的类
 * @Date 2019/4/15 15:57
 * @Version 1.0
 **/
@Data
public class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    /**
     * 改变counter计数，传入字符是否为空格
     * @param character
     * @return
     */
    public WordCounter accumulate(Character character) {
        if (Character.isWhitespace(character)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    /**
     * 合并操作，把两个WordCounter计数器加起来
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }
}
