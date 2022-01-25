import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import pages.ArticlePage;
import utils.ScreenshotForAllure;

import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

@Tag("ui")
@Tag("smoke")
@DisplayName("Search for a keyword")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UITests {

    private static final String baseUrl = System.getProperty("baseUrl");
    private static final String keyword = "AlphaSense";
    private static final String pathSegment = "doc/PR-386ea743f2a90399fb0e4300ddf37d0697abc743";
    private ArticlePage articlePage;
    private ElementsCollection elementsCollection;

    @BeforeAll
    void setUp() throws IOException {
        articlePage = open(baseUrl + pathSegment, ArticlePage.class);
        articlePage.searchForAdditionalKeywords.click();
        articlePage.textArea.sendKeys(keyword);
        articlePage.textArea.sendKeys(Keys.ENTER);

        articlePage.searchContainer.shouldBe(visible);
        elementsCollection = articlePage.searchContainerResults;
        elementsCollection.last()
                .scrollIntoView(true)
                .click();
    }

    @AfterAll
    void shutDown() {
        Selenide.closeWebDriver();
    }

    @AfterEach
    void attach() throws IOException {
        ScreenshotForAllure.attachment();
    }

    @Test
    void searchCheck() {
        String expectedText = elementsCollection.last().innerText();
        String actualText = articlePage.highlightedElement.innerText();
        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    void failedSearchCheck() {
        String expectedText = "Just wrong text";
        String actualText = articlePage.highlightedElement.innerText();
        Assertions.assertEquals(expectedText, actualText);
    }
}
