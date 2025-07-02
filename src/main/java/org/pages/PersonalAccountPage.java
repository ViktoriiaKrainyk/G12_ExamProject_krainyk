package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderElement;

public class PersonalAccountPage extends ParentPage {

    public PersonalAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public PersonalAccountPage checkIsRedirectToPersonalAccountPage() {
        checkIsRedirectToExpectedPage(baseUrl + "/user");
        return this;
    }
}
