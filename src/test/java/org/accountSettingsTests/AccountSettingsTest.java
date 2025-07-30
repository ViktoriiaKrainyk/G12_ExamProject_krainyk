package org.accountSettingsTests;

import org.baseTest.BaseTest;
import org.data.UserData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.data.TestLoginData.VALID_LOGIN_UI;

public class AccountSettingsTest extends BaseTest {
    final UserData INITIAL_USER = new UserData("Viktoriia", "Krainyk", 21, 4, 1993);
    final UserData UPDATED_USER = new UserData("Vika", "Krain", 3, 12, 2013);

    final String DATASUCCESSFULLYSAVEDMESSAGE = "Відмінно! Данні успішно збережені";

    @Before
    public void checkContactInfoAndUpdateIfNeeded() {
        logger.info("Pre condition - reset contact info to default state");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .updateUserContactInfoIfNeeded(INITIAL_USER.name, INITIAL_USER.surname, INITIAL_USER.dayOfBirth, INITIAL_USER.monthOfBirth, INITIAL_USER.yearOfBirth, DATASUCCESSFULLYSAVEDMESSAGE);
    }

    @Test
    public void TC02_updateContanctInfoTest() {
        pageProvider.getHomePage()
                .openAuthFormAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .checkIsContactInfoTabIsActive()
                .checkUserContactInfo(INITIAL_USER.name, INITIAL_USER.surname, INITIAL_USER.dayOfBirth, INITIAL_USER.monthOfBirth, INITIAL_USER.yearOfBirth, VALID_LOGIN_UI)
                .updateUserContactInfo(UPDATED_USER.name, UPDATED_USER.surname, UPDATED_USER.dayOfBirth,UPDATED_USER.monthOfBirth, UPDATED_USER.yearOfBirth, DATASUCCESSFULLYSAVEDMESSAGE)
                .refreshPersonalAccountPage()
                .checkUserContactInfo(UPDATED_USER.name, UPDATED_USER.surname, UPDATED_USER.dayOfBirth,UPDATED_USER.monthOfBirth, UPDATED_USER.yearOfBirth, VALID_LOGIN_UI);
    }

    @After
    public void resetContactInfo() {
        logger.info("Post condition - reset contact info to previous state");
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .updateUserContactInfo(INITIAL_USER.name, INITIAL_USER.surname, INITIAL_USER.dayOfBirth, INITIAL_USER.monthOfBirth, INITIAL_USER.yearOfBirth, DATASUCCESSFULLYSAVEDMESSAGE);
    }
}
