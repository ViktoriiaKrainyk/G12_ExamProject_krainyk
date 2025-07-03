package org.accountSettingsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

import static org.data.TestData.VALID_LOGIN_UI;

public class AccountSettingsTests extends BaseTest {
    final String NAME = "Viktoriia";
    final String SURNAME = "Krainyk";
    final String DATEOFBIRTH = "21";
    final String MONTHOFBIRTH = "04";
    final String YEAROFBIRTH = "1993";


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
                .checkValueInInputBirthday(String.format("%s.%s.%s", DATEOFBIRTH, MONTHOFBIRTH, YEAROFBIRTH))
                .checkValueInInputEmail(VALID_LOGIN_UI)

        ;

    }
}
