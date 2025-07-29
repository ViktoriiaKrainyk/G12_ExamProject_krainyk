package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class HealthAndCarePage extends ParentPage{
    public HealthAndCarePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h1[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfHealthAndCarePage;

    @Override
    protected String getRelativeUrl() {
        return "/category/health-and-care";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HealthAndCarePage checkIsRedirectToHealthAndCarePage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Здоров'я і догляд");
        checkIsElementDisplayed(breadCrumbsOfHealthAndCarePage, "Bread Crumbs of HealthAndCarePage");
        checkTextInElement(breadCrumbsOfHealthAndCarePage, "Здоров'я і догляд");
        logger.info("The HealthAndCarePage is opened");
        return this;
    }
}
