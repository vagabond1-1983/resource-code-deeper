package com.vaga.selenide;

import com.codeborne.selenide.Configuration;
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
        $("#kw").setValue("selenide");
        $("#su").submit();
        Thread.sleep(3000);
        // 断言
        $$("h3 > a").shouldHave(size(10));
        $("h3 > a").setValue(String.valueOf(text("selenide_百度翻译")));
    }
}
