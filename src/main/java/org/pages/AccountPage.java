package org.pages;

import org.openqa.selenium.WebDriver;
import org.pages.elements.HeaderElement;

public class AccountPage extends ParentPage {

    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
}
