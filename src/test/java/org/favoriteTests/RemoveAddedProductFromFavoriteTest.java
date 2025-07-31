package org.favoriteTests;

import org.baseTest.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class RemoveAddedProductFromFavoriteTest extends BaseTest {
    final String MONITOR_PRICE_CHANGES_CHOICE = "yes";
    static String PRODUCT_ID;
    static int COUNT_OF_FAVORITES;
    static int COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT;

    @Before
    public void addFavorite() {
        logger.info("Precondition - delete test");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded();
        COUNT_OF_FAVORITES = pageProvider.getHomePage().getHeaderElement().getFavoritesCount();
        COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT = COUNT_OF_FAVORITES + 1;
        pageProvider.getHomePage()
                .getHeaderElement()
                .navigateToSaleMenuAndCheck()
                .clickOnRandomProductItem()
                .checkIsRedirectToProductPage()
                .clickOnAddToFavoriteButton()
                .checkMonitorPriceChangesPopupIsDisplayed()
                .clickOnMonitorPriceChangesButton(MONITOR_PRICE_CHANGES_CHOICE);
        PRODUCT_ID = pageProvider.getProductPage().getLastAddedProductId();
        pageProvider.getProductPage()
                .getHeaderElement()
                .checkFavoritesCount(COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT)
                .clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .clickOnExitButton();
        ;
    }

    @Test
    public void TC05_removeAddedProductFromFavorite() {
        pageProvider.getHomePage()
                .openAuthFormAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .checkLoginSuccessWithFavoritesCount(COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT)
                .clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .checkIsProductInFavoritesByProductId(PRODUCT_ID)
                .checkMonitorPriceChangesCheckbox(MONITOR_PRICE_CHANGES_CHOICE)
                .removeItemFromFavoritesByProductId(PRODUCT_ID)
                .refreshFavoritePage()
                .getHeaderElement().checkFavoritesCount(COUNT_OF_FAVORITES)
        ;
    }
}
