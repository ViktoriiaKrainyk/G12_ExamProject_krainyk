package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

import java.util.NoSuchElementException;

public class ProductPage extends ParentPage {
    private static String lastAddedProductId;

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@class='product-title']")
    private WebElement productTitle;

    @FindBy(xpath = "//div[contains(@class,'product__to-favourite')]")
    private WebElement buttonAddToFavorite;

    @FindBy(xpath = "//div[@class='product-item__button']/div[@class='button buy']")
    private WebElement buttonBuy;

    @FindBy(xpath = "//div[@id='popup__window']//div[@class='popup-content']/div[@class='confirm-wish-list-changes specify-message-block']")
    private WebElement popupWindow;

    @FindBy(xpath = "//div[@id='popup__window']//div[@class='popup-content']//div[@class='base-button cancel']")
    private WebElement buttonDoNotMonitorPriceChanges;

    @FindBy(xpath = "//div[@id='popup__window']//div[@class='popup-content']//div[@class='button apply']")
    private WebElement buttonMonitorPriceChanges;

    @Override
    protected String getRelativeUrl() {
        return "/product/([^/?#]+)";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }


    public ProductPage checkIsRedirectToProductPage() {

        checkUrl();
        checkIsElementDisplayed(productTitle, "Product Title");
        checkIsElementDisplayed(buttonAddToFavorite, "Button Add to Favorite");
        checkIsElementDisplayed(buttonBuy, "Button Buy");
        logger.info("The ProductPage is opened");
        return this;
    }

    public ProductPage clickOnAddToFavoriteButton() {
        clickOnElement(buttonAddToFavorite, "Button Add to Favorite");
        return this;
    }

    public ProductPage checkMonitorPriceChangesPopupIsDisplayed() {
        webDriverWait15.until(ExpectedConditions.visibilityOf(popupWindow));
        checkTextInElement(popupWindow, "Хочете отримувати сповіщення про зниження ціни на товар?");
        logger.info("Monitor Price Changes popup is displayed");
        return this;
    }

    public ProductPage clickOnMonitorPriceChangesButton(String userChoice) {
        userChoice = userChoice.trim().toLowerCase();
        switch (userChoice) {
            case "yes":
                clickOnElement(buttonMonitorPriceChanges, "Button Monitor Price Changes");
                logger.info("User chose to monitor price changes for the product");
                break;
            case "no":
                clickOnElement(buttonDoNotMonitorPriceChanges, "Button Do Not Monitor Price Changes");
                logger.info("User chose not to monitor price changes for the product");
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "Invalid input: '%s'. Expected 'yes' or 'no'.", userChoice));
        }

        webDriverWait15.until(ExpectedConditions.invisibilityOf(popupWindow));
        String productId = buttonAddToFavorite.getAttribute("data-id");
        if (productId == null || productId.isEmpty()) {
            String errorMsg = "Product ID is not found in the Add to Favorite button";
            logger.error(errorMsg);
            throw new NoSuchElementException(errorMsg);
        }

        logger.info("Product article '" + productId + "' was added to favorite");
        this.lastAddedProductId = productId;
        return this;
    }

    public String getLastAddedProductId() {
        logger.info("Last added product ID: " + lastAddedProductId);
        return lastAddedProductId;
    }



}
