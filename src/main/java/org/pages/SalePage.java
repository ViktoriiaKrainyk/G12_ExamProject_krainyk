package org.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

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

//    public ProductPage clickOnProductItem(int itemIndex) {
//        waitUntilProductItemsStabilize();
//
//        List<WebElement> productItems = getListOfProductItems();
//        logger.info("Number of product items found: " + productItems.size());
//
//        if (itemIndex < 1 || itemIndex > productItems.size()) {
//            throw new IndexOutOfBoundsException(String.format(
//                    "Invalid itemIndex=%d. Must be between 1 and %d (inclusive).", itemIndex, productItems.size()));
//        }
//
//        WebElement productItem = productItems.get(itemIndex - 1);
//
//        // Знаходимо активну зону — картинку
//        WebElement imageLink = productItem.findElement(By.cssSelector("a.simple-slider-list__image"));
//
//        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView({block: 'center'});", imageLink);
//        waitUntilElementIsFullyVisible(imageLink);
//
//        webDriverWait10.until(ExpectedConditions.elementToBeClickable(imageLink));
//        new Actions(webDriver).moveToElement(imageLink).click().perform();
//
//        logger.info("Clicked on slider product image at index: " + itemIndex);
//        return new ProductPage(webDriver);
//    }

//    private void waitUntilProductItemsStabilize() {
//        int stableCount = 0;
//        int previousSize = -1;
//
//        for (int i = 0; i < 10; i++) {
//            List<WebElement> items = getListOfProductItems();
//            int currentSize = items.size();
//
//            if (currentSize == previousSize) {
//                stableCount++;
//                if (stableCount >= 2) return;
//            } else {
//                stableCount = 0;
//            }
//
//            previousSize = currentSize;
//
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//    }
//
//    private void waitUntilElementIsFullyVisible(WebElement element) {
//        webDriverWait10.until(driver -> {
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            return (Boolean) js.executeScript(
//                    "const rect = arguments[0].getBoundingClientRect();" +
//                            "return (" +
//                            "rect.top >= 0 && rect.left >= 0 && " +
//                            "rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && " +
//                            "rect.right <= (window.innerWidth || document.documentElement.clientWidth));",
//                    element
//            );
//        });
//    }
}
