package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class PharmacyCosmeticsMenu extends ParentPage {

    @FindBy(xpath = "//h1[contains(@class, 'page-header')]")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfPharmacyCosmeticsPage;

    public PharmacyCosmeticsMenu(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/category/aptechnaya-kosmetika";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PharmacyCosmeticsMenu checkIsRedirectToPharmacyCosmeticsMenu() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Дерматокосметика");
        checkIsElementDisplayed(breadCrumbsOfPharmacyCosmeticsPage, "Bread Crumbs of PharmacyCosmeticsPage");
        checkTextInElement(breadCrumbsOfPharmacyCosmeticsPage, "Дерматокосметика");
        logger.info("The PharmacyCosmeticsPage is opened");
        return this;
    }
}
