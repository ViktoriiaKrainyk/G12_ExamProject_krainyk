package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

import java.util.*;
import java.util.NoSuchElementException;

public class SalePage extends ParentPage {
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
                By.xpath(productItemLocator));
    }

    public ProductPage clickOnRandomProductItem() {
        List<WebElement> productItems = getListOfProductItems();
        if (productItems.isEmpty()) {
            throw new NoSuchElementException("No product items found on the page.");
        }
        int randomIndex = new Random().nextInt(productItems.size());
        WebElement productItem = productItems.get(randomIndex);

        WebElement imageLink = productItem.findElement(By.cssSelector("a.simple-slider-list__image"));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", imageLink);
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(imageLink));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(imageLink).click().perform();

        logger.info("Clicked on random product item at index: " + (randomIndex + 1));
        return new ProductPage(webDriver);
    }

    public ProductPage clickOnRandomProductItemExcluding(List<String> excludedProductIds) {
        List<WebElement> productItems = getListOfProductItems();
        if (productItems.isEmpty()) {
            throw new NoSuchElementException("No product items found on the page.");
        }

        List<WebElement> availableItems = new ArrayList<>();
        Map<WebElement, String> elementToProductId = new HashMap<>();

        for (WebElement item : productItems) {
            String productId = item.getAttribute("data-id");
            if (productId != null && !excludedProductIds.contains(productId)) {
                availableItems.add(item);
                elementToProductId.put(item, productId);
            }
        }

        if (availableItems.isEmpty()) {
            throw new NoSuchElementException("No unique product items available (all are excluded).");
        }

        int randomIndex = new Random().nextInt(availableItems.size());
        WebElement selectedItem = availableItems.get(randomIndex);
        String selectedProductId = elementToProductId.get(selectedItem);

        WebElement imageLink = selectedItem.findElement(By.cssSelector("a.simple-slider-list__image"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", imageLink);
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(imageLink));
        new Actions(webDriver).moveToElement(imageLink).click().perform();

        logger.info("Clicked on product item with ID: " + selectedProductId + " at index: " + randomIndex);
        return new ProductPage(webDriver);
    }
}
