package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class FaceCosmeticsPage extends ParentPage {
    public FaceCosmeticsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfFaceCosmeticsPage;

    @Override
    protected String getRelativeUrl() {
        return "/category/cosmetics-for-the-face";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public FaceCosmeticsPage checkIsRedirectToFaceCosmeticsPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Косметика для обличчя");
        checkIsElementDisplayed(breadCrumbsOfFaceCosmeticsPage, "Bread Crumbs of FaceCosmeticsPage");
        checkTextInElement(breadCrumbsOfFaceCosmeticsPage, "Догляд за обличчям");
        logger.info("The FaceCosmeticsPage is opened");
        return this;
    }
}
