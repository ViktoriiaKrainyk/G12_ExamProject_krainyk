package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class HairCosmeticsPage extends ParentPage {

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfHairCosmeticsPage;

    public HairCosmeticsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/category/hair-cosmetics";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HairCosmeticsPage checkIsRedirectToHairCosmeticsPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Косметика для волосся");
        checkIsElementDisplayed(breadCrumbsOfHairCosmeticsPage, "Bread Crumbs of HairCosmeticsPage");
        checkTextInElement(breadCrumbsOfHairCosmeticsPage, "Волосся");
        logger.info("The HairCosmeticsPage is opened");
        return this;
    }
}
