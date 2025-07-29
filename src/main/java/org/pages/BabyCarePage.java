package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class BabyCarePage extends ParentPage {
    public BabyCarePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfBabyCarePage;

    @Override
    protected String getRelativeUrl() {
        return "/category/baby-care";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public BabyCarePage checkIsRedirectToBabyCarePage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Товари для дітей і батьків");
        checkIsElementDisplayed(breadCrumbsOfBabyCarePage, "Bread Crumbs of BabyCarePage");
        checkTextInElement(breadCrumbsOfBabyCarePage, "Дітям і мамам");
        logger.info("The BabyCarePage is opened");
        return this;
    }
}
