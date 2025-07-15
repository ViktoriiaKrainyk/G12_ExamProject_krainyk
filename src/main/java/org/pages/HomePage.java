package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderElement;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class HomePage extends ParentPage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage openHomePage() {
        webDriver.get(baseUrl);
        logger.info("Home page was opened with URL: " + baseUrl);
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public HomePage openLoginPopUpAndFillLoginFormWithValidData() {
        openHomePage();
        getHeaderElement()
                .clickOnLoginToAccountButton()
                .getAuthForm()
                    .enterTextInInputEmail(VALID_LOGIN_UI)
                    .enterTextInInputPassword(VALID_PASSWORD_UI)
                    .clickOnButtonSignIn(this);
        return this;
    }

    public HomePage checkIsRedirectToHomePage() {
        checkIsRedirectToExpectedPage(baseUrl);
        return this;
    }

    public HomePage openLoginPopUpAndFillLoginFormIfNeeded() {
        getHeaderElement();
        if (getHeaderElement().isAccountButtonForLoggedUser()) {
            logger.info("User is already logged in");
        } else {
            getHeaderElement().clickOnLoginToAccountButton()
                    .getAuthForm()
                        .enterTextInInputEmail(VALID_LOGIN_UI)
                        .enterTextInInputPassword(VALID_PASSWORD_UI)
                        .clickOnButtonSignIn(this);
            checkIsRedirectToHomePage();
            logger.info("User was logged in.");
        }
        return this;
    }
}
