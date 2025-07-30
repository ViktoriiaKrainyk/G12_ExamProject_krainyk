package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}
