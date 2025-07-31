package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FavoritePage extends PersonalAccountPage {
    private String productListItemLocator = "//li[@class='product-list__item']";

    public FavoritePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//li[@data-tab='wish-list']")
    private WebElement wishListTab;

    @Override
    protected String getRelativeUrl() {
        return "/user#wish-list";
    }

    public FavoritePage checkIsRedirectToFavoritePage() {
        checkUrl();
        checkTabsNames();
        checkIsWishListTabIsActive();
        logger.info("The AccountPage is opened");
        return this;
    }

    public PersonalAccountPage checkIsWishListTabIsActive() {
        ((JavascriptExecutor) webDriver).executeScript("window.focus();");
        checkOnlyOneTabIsActive();
        checkIsTabActive(wishListTab, "Wish list tab");
        return this;
    }

    private List<WebElement> getListOfProductItems() {
        return webDriver.findElements(
                By.xpath(String.format(productListItemLocator)));
    }

    // Method to check how many products are in the favorite list
    public FavoritePage checkFavoriteListSize(int expectedSize) {
        List<WebElement> productItems = getListOfProductItems();
        if (productItems.size() != expectedSize) {
            throw new AssertionError("Expected " + expectedSize + " items in favorite list, but found " + productItems.size());
        }
        logger.info("Favorite list contains " + expectedSize + " items");
        return this;
    }

    public FavoritePage removeAllItemsFromFavorites() {
        List<WebElement> productItems = getListOfProductItems();
        int initialSize = productItems.size();
        int countOfRemovedItems = 0;
        logger.info("Starting to remove items from favorites. Initial size: " + initialSize);

        if (productItems.isEmpty()) {
            logger.info("Favorites list is already empty. No items to remove.");
            return this;
        }

        while (productItems.size() > 0) {
            List<WebElement> removeButtons = webDriver.findElements(By.cssSelector(".link-remove.remove"));
            if (removeButtons.isEmpty()) break;

            WebElement button = removeButtons.get(0);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", button);
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(button));
            button.click();
            webDriverWait10.until(ExpectedConditions.stalenessOf(button));

            countOfRemovedItems++;
            logger.info("Removed an item from favorites. Removed items: " + countOfRemovedItems + "/" + initialSize);
        }

        List<WebElement> remainingItems = getListOfProductItems();
        if (!remainingItems.isEmpty()) {
            logger.error("Not all favorite items were removed. Remaining: " + remainingItems.size());
            throw new AssertionError("Favorites are not empty after deletion. Items left: " + remainingItems.size());
        }

        logger.info("All favorite items were successfully removed.");
        return this;
    }

    public FavoritePage removeItemFromFavoritesByProductId(String dataId) {
        String itemXpath = String.format("//li[@data-id='%s']", dataId);
        String removeButtonXpath = itemXpath + "//div[contains(@class, 'link-remove')]";

        List<WebElement> items = webDriver.findElements(By.xpath(itemXpath));
        if (items.isEmpty()) {
            logger.warn("No favorite item found with productId = " + dataId);
            return this;
        }

        WebElement item = items.get(0);
        WebElement removeButton = webDriver.findElement(By.xpath(removeButtonXpath));

        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", removeButton);
        webDriverWait10.until(ExpectedConditions.elementToBeClickable(removeButton));
        removeButton.click();
        webDriverWait10.until(ExpectedConditions.stalenessOf(item));

        checkIsElementNotDisplayed(item);
        logger.info("Removed favorite item with productId = " + dataId);
        return this;
    }


    public FavoritePage checkIsProductInFavoritesByProductId(String productId) {
        String itemXpath = String.format("//li[@data-id='%s']", productId);
        List<WebElement> items = webDriver.findElements(By.xpath(itemXpath));

        if (items.isEmpty()) {
            throw new AssertionError("Product with ID " + productId + " is not found in favorites.");
        }

        WebElement item = items.get(0);
        checkIsElementDisplayed(item, "Product item with ID: " + productId);
        logger.info("Product with ID " + productId + " is present in favorites.");
        return this;
    }

    public FavoritePage checkMonitorPriceChangesCheckbox(String expectedState) {
        expectedState = expectedState.trim().toLowerCase();
        WebElement checkbox = webDriver.findElement(By.cssSelector("input[id^='set_price_inform-']"));
        boolean actualState = isCheckboxChecked(checkbox);

        switch (expectedState) {
            case "yes":
                if (!actualState) {
                    throw new AssertionError("The Monitor Price Changes checkbox expected to be checked, but it was UNCHECKED.");
                }
                logger.info("The Monitor Price Changes checkbox is CHECKED as expected");
                break;
            case "no":
                if (actualState) {
                    throw new AssertionError("The Monitor Price Changes checkbox expected to be unchecked, but it was CHECKED.");
                }
                logger.info("The Monitor Price Changes checkbox is UNCHECKED as expected");
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "Invalid input: '%s'. Expected 'yes' or 'no'.", expectedState));
        }
        return this;
    }

    public FavoritePage refreshFavoritePage() {
        refreshPage();
        checkIsRedirectToFavoritePage();
        return this;
    }

    public List<String> getAllProductIdsInFavorites() {
        List<WebElement> productElements = webDriver.findElements(By.xpath("//li[@class[contains(., 'product-list__item')]]"));

        List<String> productIds = new ArrayList<>();
        for (WebElement element : productElements) {
            String productId = element.getAttribute("data-id");
            if (productId != null && !productId.isEmpty()) {
                productIds.add(productId);
            }
        }
        logger.info("Found " + productIds.size() + " products in favorites: " + productIds);
        return productIds;
    }
}
