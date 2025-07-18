package org.loginTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.AuthFormFieldsData.*;
import static org.data.TestLoginData.VALID_LOGIN_UI;
import static org.data.TestLoginData.VALID_PASSWORD_UI;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest() {
        pageProvider.getHomePage()
                .openHomePage()
                .checkIsRedirectToHomePage()
                .getHeaderElement()
                .checkLoginToAccountButtonIsDisplayed()
                .checkTextInLoginToAccountButton(LOGIN_TO_ACCOUNT_BUTTON_TEXT)

                .checkFavoritesCountIsNotDisplayed()
                .clickOnLoginToAccountButton()
                .getAuthForm()
                .authFormVerification(
                        AUTH_FORM_TITLE,
                        EMAIL_PLACEHOLDER,
                        PASSWORD_PLACEHOLDER,
                        SIGN_IN_BUTTON_TEXT,
                        FORGOT_PASSWORD_BUTTON_TEXT,
                        SIGN_UP_BUTTON_TEXT)
                .enterTextInInputEmail(VALID_LOGIN_UI)
                .enterTextInInputPassword(VALID_PASSWORD_UI)
                .clickOnButtonSignIn(pageProvider.getHomePage())
                .getHeaderElement().getAuthForm().checkAuthFormIsNotDisplayed();

        pageProvider.getHomePage().checkIsRedirectToHomePage()
                .getHeaderElement()
                .checkLoginSuccess(ACCOUNT_BUTTON_TEXT);
    }
}
