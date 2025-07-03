package org.accountSettingsTests;

import org.baseTest.BaseTest;
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



    @Test
    public void updateContanctInfoTest() {
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .checkTabsNames()
                .checkIsContactInfoTabIsActive()
                .checkValueInInputName(NAME)
                .checkValueInInputSurname(SURNAME)
                .checkIsCalendarNotDisplayed()
                .checkValueInInputBirthday(String.format("%02d.%02d.%d", DATEOFBIRTH, MONTHOFBIRTH, YEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI)
                .enterTextIntoInputName(UPDATEDNAME)
                .enterTextIntoInputSurname(UPDATEDSURNAME)
                .setBirthdayValue(UPDATEDDATEOFBIRTH,UPDATEDMONTHOFBIRTH,UPDATEDYEAROFBIRTH)
                .checkIsCalendarNotDisplayed()
                .checkValueInInputBirthday(String.format("%02d.%02d.%d", UPDATEDDATEOFBIRTH, UPDATEDMONTHOFBIRTH, UPDATEDYEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI)
                .clickOnSaveButton()
                .getMessagePopUp().checkMessagePopUpIsDisplayed()
                .checkMessageTextInMessagePopUp("Відмінно! Данні успішно збережені")
                .closeMessagePopUp()
                .getHeaderElement().clickOnAccountButton();

        pageProvider.getPersonalAccountPage()
                .checkValueInInputName(UPDATEDNAME)
                .checkValueInInputSurname(UPDATEDSURNAME)
                .checkValueInInputBirthday(String.format("%02d.%02d.%d", UPDATEDDATEOFBIRTH, UPDATEDMONTHOFBIRTH, UPDATEDYEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI)
        ;

    }
}
