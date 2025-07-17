package org.pages.elements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pages.CommonActionsWithElements;

import java.time.Duration;

public class AuthForm extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//form[@id='form-auth']")
    private WebElement authForm;

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

    public AuthForm(WebDriver webDriver) {
        super(webDriver);
    }

    public AuthForm authFormVerification(String formTitle,
                                         String emailPlaceholder,
                                         String passwordPlaceholder,
                                         String signInButtonText,
                                         String forgotPasswordText,
                                         String signUpButtonText) {
        checkAuthFormIsDisplayed();
        checkAuthFormTitleIsDisplayed();
        checkTextInAuthFormTitle(formTitle);
        checkEmailInputIsDisplayed();
        checkPlaceholderInEmailInput(emailPlaceholder);
        checkPasswordInputIsDisplayed();
        checkPlaceholderInPasswordInput(passwordPlaceholder);
        checkSignInButtonIsDisplayed();
        checkTextInSignInButton(signInButtonText);
        checkForgotPasswordButtonIsDisplayed();
        checkTextInForgotPasswordButton(forgotPasswordText);
        checkSignUpButtonIsDisplayed();
        checkTextInSignUpButton(signUpButtonText);
        checkCloseAuthFormButtonIsDisplayed();
        return this;
    }

    private AuthForm checkAuthFormIsDisplayed() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(authForm));
        logger.info("Auth Form is displayed as expected");
        return this;
    }

    private AuthForm checkAuthFormTitleIsDisplayed() {
        checkIsElementDisplayed(headerAuthForm);
        return this;
    }

    private AuthForm checkTextInAuthFormTitle(String expectedTitle) {
        checkTextInElement(headerAuthForm, expectedTitle);
        return this;
    }

    private AuthForm checkEmailInputIsDisplayed() {
        checkIsElementDisplayed(inputEmail);
        return this;
    }

    private AuthForm checkPlaceholderInEmailInput(String expectedPlaceholder) {
        checkPlaceholderInElement(inputEmail, expectedPlaceholder);
        return this;
    }

    private AuthForm checkPasswordInputIsDisplayed() {
        checkIsElementDisplayed(inputPassword);
        return this;
    }

    private AuthForm checkPlaceholderInPasswordInput(String expectedPlaceholder) {
        checkPlaceholderInElement(inputPassword, expectedPlaceholder);
        return this;
    }

    private AuthForm checkSignInButtonIsDisplayed() {
        checkIsElementDisplayed(buttonSignIn);
        return this;
    }

    private AuthForm checkTextInSignInButton(String expectedText) {
        checkTextInElement(buttonSignIn, expectedText);
        return this;
    }

    private AuthForm checkForgotPasswordButtonIsDisplayed() {
        checkIsElementDisplayed(buttonForgotPassword);
        return this;
    }

    private AuthForm checkTextInForgotPasswordButton(String expectedText) {
        checkTextInElement(buttonForgotPassword, expectedText);
        return this;
    }

    private AuthForm checkSignUpButtonIsDisplayed() {
        checkIsElementDisplayed(linkSignUp);
        return this;
    }

    private AuthForm checkTextInSignUpButton(String expectedText) {
        checkTextInElement(linkSignUp, expectedText);
        return this;
    }

    private AuthForm checkCloseAuthFormButtonIsDisplayed() {
        checkIsElementDisplayed(closeAuthFormButton);
        return this;
    }

    public AuthForm enterTextInInputEmail(String email) {
        clearAndEnterTextToElement(inputEmail, email);
        return this;
    }

    public AuthForm enterTextInInputPassword(String password) {
        clearAndEnterTextToElement(inputPassword, password);
        return this;
    }

    public <T> T clickOnButtonSignIn(T currentPage) {
        clickOnElement(buttonSignIn);
        logger.info("Sign In button was clicked");
        return currentPage;
    }
}
