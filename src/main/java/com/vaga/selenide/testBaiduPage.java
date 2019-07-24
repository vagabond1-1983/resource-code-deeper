package com.vaga.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.vaga.selenide.pageobject.BaiduPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;

public class testBaiduPage {
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
     * https://qiita.com/tatesuke/items/0bac60172e7cfd12aeb1
     * https://github.com/yashaka/talks/tree/master/kiss-pageobjects/src/test/java/com/automician/talks/kisspageobjects/google/kiss
     *
     */
    @Test
    public void pageObjectTest() {
        BaiduPage baiduPage = open("https://www.baidu.com", BaiduPage.class);
        baiduPage
                .search("selenide")
                .shouldHaveSize(10)
                .shouldMatch(2, "虫师");

    }
}
