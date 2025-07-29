package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

public class MenCosmeticsPage extends ParentPage{
    public MenCosmeticsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[contains(@class, 'page-category')]/div/div[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfMenCosmeticsPage;

    @Override
    protected String getRelativeUrl() {
        return "/category/mugskaya-kosmetika";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public MenCosmeticsPage checkIsRedirectToMenCosmeticsPage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "Чоловіча косметика");
        checkIsElementDisplayed(breadCrumbsOfMenCosmeticsPage, "Bread Crumbs of MenCosmeticsPage");
        checkTextInElement(breadCrumbsOfMenCosmeticsPage, "Чоловікам");
        logger.info("The MenCosmeticsPage is opened");
        return this;
    }
}
