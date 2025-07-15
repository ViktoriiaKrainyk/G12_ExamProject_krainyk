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
    private WebElement LoginToAccountButton;

    @FindBy(xpath = "//a[@id='profile_desktop']//button[contains(@class,'profile')]")
    private WebElement AccountButton;

    @FindBy(xpath = "//button[@id='favorites']//span[@class='favourite-count']")
    private WebElement favoritesCount;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public AuthForm getAuthForm() {
        return new AuthForm(webDriver);
    }

    public HeaderElement checkLoginToAccountButtonIsDisplayed() {
        checkIsElementDisplayed(LoginToAccountButton);
        return this;
    }

    public HeaderElement checkLoginToAccountButtonIsNotDisplayed() {
        checkIsElementNotDisplayed(LoginToAccountButton);
        return this;
    }

    public HeaderElement checkTextInLoginToAccountButton(String expectedText ) {
        checkTextInElement(LoginToAccountButton, expectedText);
        return this;
    }

    public HeaderElement clickOnLoginToAccountButton() {
        clickOnElement(LoginToAccountButton);
        return this;
    }

    public HeaderElement checkAccountButtonIsDisplayed() {
        checkIsElementDisplayed(AccountButton);
        return this;
    }

    public HeaderElement checkTextInAccountButton(String expectedText) {
        checkTextInElement(AccountButton, expectedText);
        return this;
    }

    public boolean isAccountButtonForLoggedUser() {
       return isElementDisplayed(AccountButton);
    }

    public HeaderElement checkFavoritesCountIsDisplayed() {
        checkIsElementDisplayed(favoritesCount);
        return this;
    }

    public HeaderElement checkFavoritesCountIsNotDisplayed() {
        checkIsElementNotDisplayed(favoritesCount);
        return this;
    }

    public PersonalAccountPage clickOnAccountButton() {
        clickOnElement(AccountButton);
        logger.info("Click on Account Button");
        return new PersonalAccountPage(webDriver);
    }
}
