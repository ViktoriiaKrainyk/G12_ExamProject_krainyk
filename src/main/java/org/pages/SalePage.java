package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

import java.util.List;

public class SalePage extends ParentPage{
    private String productItemLocator = "//li[@class='simple-slider-list__item']";

    public SalePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//h1[@class='page-header']")
    private WebElement pageHeader;

    @FindBy(xpath = "//div[@class='bread-crumbs']//li/span")
    private WebElement breadCrumbsOfSalePage;

    @Override
    protected String getRelativeUrl() {
        return "/markdowned";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public SalePage checkIsRedirectToSalePage() {
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(pageHeader));
        checkTextInElement(pageHeader, "SALE!");
        checkIsElementDisplayed(breadCrumbsOfSalePage, "Bread Crumbs of SalePage");
        checkTextInElement(breadCrumbsOfSalePage, "SALE!");
        logger.info("The SalePage is opened");
        return this;
    }

    private List<WebElement> getListOfProductItems() {
        return webDriver.findElements(
                By.xpath(String.format(productItemLocator)));
    }

    public ProductPage clickOnProductItem(int itemIndex) {
        List<WebElement> productItems = getListOfProductItems();
        if (itemIndex < 1 || itemIndex > productItems.size()) {
            throw new IndexOutOfBoundsException(String.format(
                    "Invalid itemIndex=%d. Must be between 1 and %d (inclusive).", itemIndex, productItems.size()));
        }
        WebElement productItem = productItems.get(itemIndex - 1);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", productItem);
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(productItem));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(productItem).click().perform();
        logger.info("Clicked on product item at index: " + itemIndex);
        return new ProductPage(webDriver);
    }




}
