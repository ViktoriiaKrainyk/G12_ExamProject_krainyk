package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class CosmeticsForTheBodyPage extends ParentPage {
    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfCosmeticsToTheBodyPage;

    public CosmeticsForTheBodyPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/category/cosmetics-for-the-body";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public CosmeticsForTheBodyPage checkIsRedirectToCosmeticsForTheBodyPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Косметика для тіла");
        checkIsElementDisplayed(breadCrumbsOfCosmeticsToTheBodyPage, "Bread Crumbs of CosmeticsForTheBodyPage");
        checkTextInElement(breadCrumbsOfCosmeticsToTheBodyPage, "Для тіла");
        logger.info("The CosmeticsForTheBodyPage is opened");
        return this;
    }
}
