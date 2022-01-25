package ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import pages.ArticlePage;
import utils.ScreenshotForAllure;

import java.io.IOException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Tag("ui")
@Tag("smoke")
@DisplayName("Search for a keyword")
class SearchingTest {

    private static final String baseUrl = System.getProperty("baseUrl");
    private static final String keyword = "AlphaSense";
    private static final String pathSegment = "doc/PR-386ea743f2a90399fb0e4300ddf37d0697abc743";

    @AfterEach
    void shutDown() throws IOException {
        ScreenshotForAllure.attachment();
        Selenide.closeWebDriver();
    }

    @Test
    void searchCheck() {

        ArticlePage articlePage = open(baseUrl + pathSegment, ArticlePage.class);
        articlePage.searchForAdditionalKeywords.click();
        articlePage.textArea.sendKeys(keyword);
        articlePage.textArea.sendKeys(Keys.ENTER);

        articlePage.searchContainer.shouldBe(visible);
        ElementsCollection elementsCollection = articlePage.searchContainerResults;
        elementsCollection.last()
                .scrollIntoView(true)
                .click();
        String expectedText = elementsCollection.last().innerText();
        System.out.println(expectedText);
        String actualText = articlePage.highlightedElement.innerText();
        System.out.println(actualText);
        Assertions.assertEquals(expectedText, actualText);
    }
}
