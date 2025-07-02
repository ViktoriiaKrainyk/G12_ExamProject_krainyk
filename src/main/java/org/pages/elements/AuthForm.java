package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.CommonActionsWithElements;
import org.pages.HomePage;

public class AuthForm extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());

    @FindBy(xpath = "//form[@id='form-auth']//h2")
    private WebElement headerAuthForm;

    @FindBy(xpath = "//input[@name='user_login']")
    private WebElement inputEmail;

    @FindBy(xpath = "//input[@name='user_pw']")
    private WebElement inputPassword;

    @FindBy(xpath = "//form[@id='form-auth']//button[@type='submit']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[@data-popup-handler='forget-password']")
    private WebElement buttonForgotPassword;

    @FindBy(xpath = "//a[@class='auth-link']")
    private WebElement linkSignUp;

    @FindBy(xpath = "//form[@id='form-auth']//div[@class='popup-close close-icon']")
    private WebElement closeAuthFormButton;

    public AuthForm (WebDriver webDriver) {
        super(webDriver);
    }

    public AuthForm checkAuthFormTitleIsDisplayed() {
        checkIsElementDisplayed(headerAuthForm);
        return this;
    }

    public AuthForm checkTextInAuthFormTitle(String expectedTitle ) {
        checkTextInElement(headerAuthForm, expectedTitle);
        return this;
    }

    public AuthForm checkEmailInputIsDisplayed( ) {
        checkIsElementDisplayed(inputEmail);
        return this;
    }

    public AuthForm checkPlaceholderInEmailInput(String expectedPlaceholder ) {
        checkPlaceholderInElement(inputEmail, expectedPlaceholder);
        return this;
    }

    public AuthForm checkPasswordInputIsDisplayed( ) {
        checkIsElementDisplayed(inputPassword);
        return this;
    }

    public AuthForm checkPlaceholderInPasswordInput(String expectedPlaceholder ) {
        checkPlaceholderInElement(inputPassword, expectedPlaceholder);
        return this;
    }

    public AuthForm checkSignInButtonIsDisplayed() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    public AuthForm checkTextInSignInButton(String expectedText ) {
        checkTextInElement(buttonSignIn, expectedText);
        return this;
    }

    public AuthForm checkForgotPasswordButtonIsDisplayed() {
        checkIsElementDisplayed(buttonForgotPassword);
        return this;
    }

    public AuthForm checkTextInForgotPasswordButton(String expectedText ) {
        checkTextInElement(buttonForgotPassword, expectedText);
        return this;
    }

    public AuthForm checkSignUpButtonIsDisplayed() {
        checkIsElementDisplayed(linkSignUp);
        return this;
    }

    public AuthForm checkTextInSignUpButton(String expectedText ) {
        checkTextInElement(linkSignUp, expectedText);
        return this;
    }

    public AuthForm checkCloseAuthFormButtonIsDisplayed() {
        checkIsElementDisplayed(closeAuthFormButton);
        return this;
    }

    public AuthForm enterTextInInputEmail(String email ) {
        clearAndEnterTextToElement(inputEmail, email);
        return this;
    }

    public AuthForm enterTextInInputPassword(String password ) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public <T> T clickOnButtonSignIn(T currentPage) {
        clickOnElement(buttonSignIn);
        logger.info("Sign In button was clicked");
        return currentPage;
    }
}
