package org.pages;


import org.openqa.selenium.WebDriver;
import org.pages.elements.AuthForm;
import org.pages.elements.HeaderElement;

public class HomePage extends ParentPage{
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage openHomePage() {
        webDriver.get(baseUrl);
        logger.info("Home page was opened with URL: " + baseUrl);
        return this;
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }
}
