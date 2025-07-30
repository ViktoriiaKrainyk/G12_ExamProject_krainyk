package org.favoriteTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddProductToFavoriteTest extends BaseTest {
    final String MONITOR_PRICE_CHANGES_CHOICE = "yes";
    static String PRODUCT_ID;

    @Before
    public void deletedAllProductsFromFavorite() {
        logger.info("Post condition - delete test");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded();
        pageProvider.getHomePage()
                .getHeaderElement().clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .removeAllItemsFromFavorites();
        pageProvider.getPersonalAccountPage().clickOnExitButton();
    }

    @Test
    public void TC04_addProductToFavorite() {
        pageProvider.getHomePage()
                .openAuthFormAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .checkLoginSuccessWithFavoritesCount(0)
                .clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .checkFavoriteListSize(0)
                .getHeaderElement()
                .navigateToSaleMenuAndCheck()
                .clickOnRandomProductItem()
                .checkIsRedirectToProductPage()
                .clickOnAddToFavoriteButton()
                .checkMonitorPriceChangesPopupIsDisplayed()
                .clickOnMonitorPriceChangesButton(MONITOR_PRICE_CHANGES_CHOICE);
        PRODUCT_ID = pageProvider.getProductPage().getLastAddedProductId();
        pageProvider.getProductPage()
                .getHeaderElement().checkFavoritesCount(1)
                .clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .checkFavoriteListSize(1)
                .checkIsProductInFavoritesByProductId(PRODUCT_ID)
                .checkMonitorPriceChangesCheckbox(MONITOR_PRICE_CHANGES_CHOICE);

    }

    @After
    public void deleteAddedProductFromFavorite() {
        logger.info("Post condition - delete test");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded()
                .getHeaderElement().clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .removeItemFromFavoritesByProductId(PRODUCT_ID);
    }
}
