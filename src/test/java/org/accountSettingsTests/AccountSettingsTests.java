package org.accountSettingsTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class AccountSettingsTests extends BaseTest {
    @Test
    public void updateContanctInfoTest() {
        pageProvider.getHomePage()
                .openLoginPopUpAndFillLoginFormWithValidData()
                .checkIsRedirectToHomePage()
                .getHeaderElement().clickOnAccountButton()
                .checkIsRedirectToPersonalAccountPage()
                .checkTabsNames()
                .checkIsContactInfoTabIsActive()
                ;

    }
}
