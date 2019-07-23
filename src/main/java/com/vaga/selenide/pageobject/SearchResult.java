package com.vaga.selenide.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Description 搜索结果断言
 * @Date 2019/7/23 18:14
 * @Version 1.0
 **/
public class SearchResult {
    private ElementsCollection elements() {
        return $$("h3 > a");
    }

    public SearchResult shouldHaveSize(int number) {
        this.elements().shouldHaveSize(number);
        return this;
    }

    public SearchResult shouldMatch(int index, String text) {
        SelenideElement element = this.elements().get(index);
        assertThat(element.getText()).contains(text);
        return this;
    }
}
