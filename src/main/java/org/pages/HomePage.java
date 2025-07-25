package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.elements.HeaderElement;

import static org.data.TestLoginData.VALID_LOGIN_UI;
import static org.data.TestLoginData.VALID_PASSWORD_UI;

public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//div[@id='slider' and @class='banner']")
    private WebElement mainSaleBanner;

    @FindBy(xpath = "//div[@data-default-name='Товары недели']")
    private WebElement weeklyProductsSection;

    @Override
    protected String getRelativeUrl() {
        return "";
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
        checkUrl();
        webDriverWait10.until(ExpectedConditions.visibilityOf(mainSaleBanner));
        checkIsElementDisplayed(mainSaleBanner, "Sale Banner");
        webDriverWait15.until(ExpectedConditions.visibilityOf(weeklyProductsSection));
        checkIsElementDisplayed(weeklyProductsSection, "Weekly Product section");
        logger.info("The HomePage is opened");
        return this;
    }

    public HomePage openLoginPopUpAndFillLoginFormIfNeeded() {
        openHomePage();
        getHeaderElement();
        if (getHeaderElement().isAccountButtonForLoggedUser()) {
            logger.info("User is already logged in");
        } else {
            openHomePage();
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
