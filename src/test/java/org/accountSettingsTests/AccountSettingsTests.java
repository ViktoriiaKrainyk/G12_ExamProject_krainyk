package org.accountSettingsTests;

import org.baseTest.BaseTest;
import org.junit.After;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;

public class AccountSettingsTests extends BaseTest {
    final String NAME = "Viktoriia";
    final String SURNAME = "Krainyk";
    final int DATEOFBIRTH = 21;
    final int MONTHOFBIRTH = 4;
    final int YEAROFBIRTH = 1993;

    final String UPDATEDNAME = "Vika";
    final String UPDATEDSURNAME = "Krain";
    final int UPDATEDDATEOFBIRTH = 3;
    final int UPDATEDMONTHOFBIRTH = 12;
    final int UPDATEDYEAROFBIRTH = 2013;

    final String DATASUCCESSFULLYSAVEDMESSAGE = "Відмінно! Данні успішно збережені";


    @Test
    public void updateContanctInfoTest() {
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .checkTabsNames()
                .checkIsContactInfoTabIsActive();

        checkInitialContactInfo();
        updateContactInfo();
        verifyUpdatedContactInfo();
    }

    @After
    public void resetContactInfo() {
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormIfNeeded()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .enterTextIntoInputName(NAME)
                .enterTextIntoInputSurname(SURNAME)
                .setBirthdayValue(DATEOFBIRTH, MONTHOFBIRTH, YEAROFBIRTH)
                .clickOnSaveButton()
                .getMessagePopUp().checkMessagePopUpIsDisplayed()
                .checkMessageTextInMessagePopUp(DATASUCCESSFULLYSAVEDMESSAGE);
    }

    private void checkInitialContactInfo() {
        pageProvider.getPersonalAccountPage()
                .checkValueInInputName(NAME)
                .checkValueInInputSurname(SURNAME)
                .checkIsCalendarNotDisplayed()
                .checkValueInInputBirthday(formatBirthday(DATEOFBIRTH, MONTHOFBIRTH, YEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI);
    }

    private void updateContactInfo() {
        pageProvider.getPersonalAccountPage()
                .enterTextIntoInputName(UPDATEDNAME)
                .enterTextIntoInputSurname(UPDATEDSURNAME)
                .setBirthdayValue(UPDATEDDATEOFBIRTH, UPDATEDMONTHOFBIRTH, UPDATEDYEAROFBIRTH)
                .checkIsCalendarNotDisplayed()
                .checkValueInInputBirthday(formatBirthday(UPDATEDDATEOFBIRTH, UPDATEDMONTHOFBIRTH, UPDATEDYEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI)
                .clickOnSaveButton()
                .getMessagePopUp().checkMessagePopUpIsDisplayed()
                .checkMessageTextInMessagePopUp(DATASUCCESSFULLYSAVEDMESSAGE)
                .closeMessagePopUp();
    }

    private void verifyUpdatedContactInfo() {
        pageProvider.getPersonalAccountPage().getHeaderElement()
                .clickOnAccountButton()
                .checkValueInInputName(UPDATEDNAME)
                .checkValueInInputSurname(UPDATEDSURNAME)
                .checkValueInInputBirthday(formatBirthday(UPDATEDDATEOFBIRTH, UPDATEDMONTHOFBIRTH, UPDATEDYEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI);

    }

    private String formatBirthday(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

}
