package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class MakeUpPage extends ParentPage {
    public MakeUpPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h1[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfMakeupPage;

    @Override
    protected String getRelativeUrl() {
        return "/category/makeup";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public MakeUpPage checkIsRedirectToMakeupPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Макіяж");
        checkIsElementDisplayed(breadCrumbsOfMakeupPage, "Bread Crumbs of MakeUpPage");
        checkTextInElement(breadCrumbsOfMakeupPage, "Макіяж");
        logger.info("The MakeUpPage is opened");
        return this;
    }
}
