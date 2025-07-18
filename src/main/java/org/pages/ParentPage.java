package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

abstract public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl = "https://parfums.ua/ua";
    protected Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }

    abstract protected String getRelativeUrl();

    protected void checkUrl() {
        String expectedPattern = baseUrl + getRelativeUrl();
        webDriverWait10.until(ExpectedConditions.urlMatches(expectedPattern));

        Assert.assertTrue("URL is not expected\n" +
                        "Expected url: " + expectedPattern +
                        "\nActual url: " + webDriver.getCurrentUrl(),
                webDriver.getCurrentUrl().matches(expectedPattern));
    }
}
