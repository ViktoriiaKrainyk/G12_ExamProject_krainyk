package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class CosmeticsForNailsPage extends ParentPage{

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfCosmeticsForNailsPage;

    public CosmeticsForNailsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/category/cosmetics-for-nails";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public CosmeticsForNailsPage checkIsRedirectToCosmeticsForNailsPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Косметика для нігтів");
        checkIsElementDisplayed(breadCrumbsOfCosmeticsForNailsPage, "Bread Crumbs of CosmeticsForNailsPage");
        checkTextInElement(breadCrumbsOfCosmeticsForNailsPage, "Нігті");
        logger.info("The CosmeticsForNailsPage is opened");
        return this;
    }
}
