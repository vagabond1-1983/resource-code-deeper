package com.vaga.selenide.pageobject;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * @Description 百度搜索页
 * @Date 2019/7/23 18:11
 * @Version 1.0
 **/
public class Baidu {
    public Baidu open() {
        Selenide.open("https://www.baidu.com");
        return this;
    }

    public Baidu search(String text) {
        $(By.id("kw")).setValue(text);
        $("#su").submit();
        return this;
    }
}
