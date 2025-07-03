package org.loginTests;

import org.baseTest.BaseTest;
import org.data.TestData;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;
import static org.data.TestData.VALID_PASSWORD_UI;

public class LoginTests extends BaseTest {
    @Test
    public void validLoginTest() {
        pageProvider.getHomePage()
                .openHomePage()
                .getHeaderElement()
                    .checkLoginToAccountButtonIsDisplayed()
                    .checkTextInLoginToAccountButton("Вхід до кабінету")
                    .checkFavoritesCountIsNotDisplayed()
                    .clickOnLoginToAccountButton();

        authFormVerification();
        enterValidLoginData();
        checkLoginSuccess();
    }

    private void authFormVerification(){
        pageProvider.getHomePage().getHeaderElement().getAuthForm()
                .checkAuthFormIsDisplayed()
                .checkAuthFormTitleIsDisplayed()
                .checkTextInAuthFormTitle("вхід до особистого кабінету")
                .checkEmailInputIsDisplayed()
                .checkPlaceholderInEmailInput("E-mail")
                .checkPasswordInputIsDisplayed()
                .checkPlaceholderInPasswordInput("Пароль")
                .checkSignInButtonIsDisplayed()
                .checkTextInSignInButton("Увійти")
                .checkForgotPasswordButtonIsDisplayed()
                .checkTextInForgotPasswordButton("Забули пароль?")
                .checkSignUpButtonIsDisplayed()
                .checkTextInSignUpButton("Реєстрація")
                .checkCloseAuthFormButtonIsDisplayed();
    }

    private void enterValidLoginData() {
        pageProvider.getHomePage().getHeaderElement().getAuthForm()
                .enterTextInInputEmail(TestData.VALID_LOGIN_UI)
                .enterTextInInputPassword(TestData.VALID_PASSWORD_UI)
                .clickOnButtonSignIn(pageProvider.getHomePage());
    }

    private void checkLoginSuccess() {
        pageProvider.getHomePage().getHeaderElement()
                .checkAccountButtonIsDisplayed()
                .checkTextInAccountButton("Кабінет")
                .checkLoginToAccountButtonIsNotDisplayed()
                .checkFavoritesCountIsDisplayed()
                .clickOnAccountButton();
        pageProvider.getPersonalAccountPage().checkIsRedirectToPersonalAccountPage();
    }


}
