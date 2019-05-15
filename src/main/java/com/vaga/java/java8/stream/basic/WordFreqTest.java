package com.vaga.java.java8.stream.basic;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 读取一段文字，找出top 10使用频率最高的词。假定只有单词没有其他字符
 * @Date 2019/4/14 16:55
 * @Version 1.0
 **/
public class WordFreqTest {
    private String filePath = "simple.txt";
    @Test
    public void top3WordsFreqAppearsTest() throws IOException, URISyntaxException {
        Path path = Paths.get(ClassLoader.getSystemResource(filePath).toURI());
        // 单词统计，无顺序
        Map<String, Long> wordsCountMap = Files.lines(path, Charset.defaultCharset())
                .flatMap(line -> Stream.of(line.split(" ")))
                .collect((groupingBy(w -> w, counting())));
        assertThat(wordsCountMap).containsKeys("java");

        // 排序后取出前3个
        List<Map.Entry> top3Entry = wordsCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
        assertThat(top3Entry).hasSize(3).first().extracting(Map.Entry::getKey).isEqualTo("java");
    }
}
