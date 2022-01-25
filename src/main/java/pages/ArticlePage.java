package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ArticlePage {

    @FindBy(xpath = ".//*[name()='title'][contains(text(),'AlphaSense')]")
    public SelenideElement title;

    @FindBy(xpath = ".//*[@class=' CodeMirror-line ']")
    public SelenideElement searchForAdditionalKeywords;

    @FindBy(css = "textarea")
    public SelenideElement textArea;

    @FindBy(xpath = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']")
    public SelenideElement searchContainer;

    @FindBy(xpath = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']//div[@class='snippetItem-wrapper']")
    public ElementsCollection searchContainerResults;

    @FindBy(xpath = "//div[@class='ReactVirtualized__Grid__innerScrollContainer']/descendant::*[contains(@class,'snippetItem_active')]")
    public SelenideElement highlightedElement;

}