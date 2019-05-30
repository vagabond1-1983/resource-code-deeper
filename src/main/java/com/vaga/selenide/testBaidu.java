package com.vaga.selenide;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class testBaidu {
    @Test
    public void openBaidu() throws InterruptedException {
        Configuration.browser = "Chrome";
        Configuration.baseUrl = "https://www.baidu.com";
        open("/");
        $(By.id("kw")).setValue("selenide");
        $("#su").submit();
        // 断言
        $$("h3 > a").shouldHave(size(10));
    }
}
