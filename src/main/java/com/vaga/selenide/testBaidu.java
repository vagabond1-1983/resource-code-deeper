package com.vaga.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.vaga.selenide.pageobject.Baidu;
import com.vaga.selenide.pageobject.SearchResult;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class testBaidu {
    @BeforeClass
    public void init() {
        // 设置chromedriver驱动路径，如果chrome不是76，则需要设置chromedriver
        // chromedriver下载地址：https://chromedriver.storage.googleapis.com/index.html
        System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver\\chromedriver.exe");
        Configuration.browser = "Chrome";
    }
    
    @Test
    public void openBaidu() throws InterruptedException {
        Configuration.baseUrl = "https://www.baidu.com";
        open("/");
        $(By.id("kw")).setValue("selenide");
        $("#su").submit();
        // 断言
        $$("h3 > a").shouldHave(size(10));
        $$("h3 > a").get(2).should(Condition.exist);
    }

    /**
     * KISS模式Page Object实践
     * https://github.com/yashaka/talks/tree/master/kiss-pageobjects/src/test/java/com/automician/talks/kisspageobjects/google/kiss
     */
    @Test
    public void pageObjectTest() {
        new Baidu().open().search("selenide");
        new SearchResult().shouldHaveSize(10).shouldMatch(2, "虫师1");
    }
}
