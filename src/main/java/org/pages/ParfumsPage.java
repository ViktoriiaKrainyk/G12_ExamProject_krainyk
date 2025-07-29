package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class ParfumsPage extends ParentPage{
    public ParfumsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfParfumsPage;

    @Override
    protected String getRelativeUrl() {
        return "/category/parfums";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public ParfumsPage checkIsRedirectToParfumsPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Парфумерія");
        checkIsElementDisplayed(breadCrumbsOfParfumsPage, "Bread Crumbs of ParfumsPage");
        checkTextInElement(breadCrumbsOfParfumsPage, "Парфумерія");
        logger.info("The ParfumsPage is opened");
        return this;
    }
}
