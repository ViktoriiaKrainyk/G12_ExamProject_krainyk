package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class EverythingForHomePage extends ParentPage {
    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfEverythingForHomePage;

    public EverythingForHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/category/bytovaya_khimiya";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public EverythingForHomePage checkIsRedirectToEverythingForHomePage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Товари для дому");
        checkIsElementDisplayed(breadCrumbsOfEverythingForHomePage, "Bread Crumbs of EverythingForHomePage");
        checkTextInElement(breadCrumbsOfEverythingForHomePage, "Все для дому");
        logger.info("The EverythingForHomePage is opened");
        return this;
    }
}
