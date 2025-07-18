package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.PersonalAccountPage;
import org.pages.CommonActionsWithElements;

public class HeaderElement extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//div[@id='profile_desktop']//button[contains(@class,'profile')]")
    private WebElement loginToAccountButton;

    @FindBy(xpath = "//a[@id='profile_desktop']//button[contains(@class,'profile')]")
    private WebElement accountButton;

    @FindBy(xpath = "//button[@id='favorites']//span[@class='favourite-count']")
    private WebElement favoritesCount;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public AuthForm getAuthForm() {
        return new AuthForm(webDriver);
    }

    public HeaderElement checkLoginToAccountButtonIsDisplayed() {
        checkIsElementDisplayed(loginToAccountButton);
        return this;
    }

    public HeaderElement checkLoginToAccountButtonIsNotDisplayed() {
        checkIsElementNotDisplayed(loginToAccountButton);
        return this;
    }

    public HeaderElement checkTextInLoginToAccountButton(String expectedText ) {
        checkTextInElement(loginToAccountButton, expectedText);
        return this;
    }

    public HeaderElement clickOnLoginToAccountButton() {
        clickOnElement(loginToAccountButton);
        return this;
    }

    public HeaderElement checkAccountButtonIsDisplayed() {
        checkIsElementDisplayed(accountButton);
        return this;
    }

    public HeaderElement checkTextInAccountButton(String expectedText) {
        checkTextInElement(accountButton, expectedText);
        return this;
    }

    public boolean isAccountButtonForLoggedUser() {
       return isElementDisplayed(accountButton);
    }

    public HeaderElement checkFavoritesCountIsDisplayed() {
        checkIsElementDisplayed(favoritesCount, "Favorite count");
        return this;
    }

    public HeaderElement checkFavoritesCountIsNotDisplayed() {
        checkIsElementNotDisplayed(favoritesCount);
        return this;
    }

    public HeaderElement checkLoginSuccess (String accountButtonText) {
                checkAccountButtonIsDisplayed();
                checkTextInAccountButton(accountButtonText);
                checkLoginToAccountButtonIsNotDisplayed();
                checkFavoritesCountIsDisplayed();;
        return this;
    }

    public PersonalAccountPage clickOnAccountButton() {
        clickOnElement(accountButton);
        logger.info("Click on Account Button");
        return new PersonalAccountPage(webDriver);
    }
}
