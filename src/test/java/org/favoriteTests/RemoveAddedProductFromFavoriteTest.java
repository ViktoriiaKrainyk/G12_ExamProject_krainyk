package org.favoriteTests;

import org.baseTest.BaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RemoveAddedProductFromFavoriteTest extends BaseTest {
    final String MONITOR_PRICE_CHANGES_CHOICE = "no";
    static String PRODUCT_ID;
    static int COUNT_OF_FAVORITES;
    static int COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT;

    @Before
    public void prepareTestWithSingleNewFavoriteItem() {
        logger.info("Precondition - delete test");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded();

        COUNT_OF_FAVORITES = getFavoritesCountWithCleanupIfNeeded();
        COUNT_OF_FAVORITES_AFTER_ADDING_PRODUCT = COUNT_OF_FAVORITES + 1;

        List<String> existingFavoriteProductIds = pageProvider.getHomePage()
                .getHeaderElement()
                .clickOnFavoriteButton()
                .checkIsRedirectToFavoritePage()
                .getAllProductIdsInFavorites();

            pageProvider.getHomePage()
                    .getHeaderElement()
                    .navigateToSaleMenuAndCheck()
                    .clickOnRandomProductItemExcluding(existingFavoriteProductIds)
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
                .getHeaderElement().checkFavoritesCount(COUNT_OF_FAVORITES);
    }

    public int getFavoritesCountWithCleanupIfNeeded() {
        int count = pageProvider.getHomePage().getHeaderElement().getFavoritesCount();
        if (count > 5) {
            logger.info("Favorites count is more than 5. Cleaning up...");
            pageProvider.getHomePage()
                    .getHeaderElement()
                    .clickOnFavoriteButton()
                    .checkIsRedirectToFavoritePage();

            List<String> allProductIds = pageProvider.getFavoritePage().getAllProductIdsInFavorites();
            int itemsToRemove = count - 5;

            for (int i = 0; i < itemsToRemove; i++) {
                String productId = allProductIds.get(i);
                pageProvider.getFavoritePage()
                        .removeItemFromFavoritesByProductId(productId);
            }

            pageProvider.getFavoritePage().refreshFavoritePage();
            count = pageProvider.getHomePage().getHeaderElement().getFavoritesCount();
            logger.info("Favorites cleaned. New count: " + count);
        }
        return count;
    }

}
