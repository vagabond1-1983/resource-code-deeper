package com.vaga.selenide.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

/**
 * @Description 百度搜索页
 * @Date 2019/7/23 18:11
 * @Version 1.0
 **/
public class BaiduPage {
    @FindBy(how = How.ID, id = "kw")
    private SelenideElement searchBox;

    @FindBy(how = How.CSS, css = "#su")
    private SelenideElement submit;

    public BaiduPage open() {
        Selenide.open("https://www.baidu.com");
        return this;
    }

    public SearchResult search(String text) {
        searchBox.setValue(text);
        submit.submit();
        return page(SearchResult.class);
    }
}
