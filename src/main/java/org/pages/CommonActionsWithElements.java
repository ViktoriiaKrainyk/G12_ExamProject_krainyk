package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Initialize the elements described in this class in FindBy annotations
    }

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered in element");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
            logger.info("Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed");
            } else {
                logger.info("Element is not displayed");
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found, so it is not displayed");
            return false;
        }
    }

    protected void checkIsElementDisplayed(WebElement webElement) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement));
        logger.info("Element is displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed, but it should not be", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    protected void checkPlaceholderInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getAttribute("placeholder");
        Assert.assertEquals("Text in placeholder does not match expected text", expectedText, actualText);
        logger.info("Text in placeholder matches expected text: " + expectedText);
    }

    protected String getValueFromElement(WebElement webElement) {
        try {
            String value = webElement.getAttribute("value");
            logger.info("Value from element = " + value);
            return value != null ? value : "";
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return "";
        }
    }

    protected void checkValueInElement(WebElement webElement, String expectedValue) {
        Assert.assertEquals("Value in element does not match expected value", expectedValue, getValueFromElement(webElement));
        logger.info("Value in element matches expected value: " + expectedValue);
    }

    protected void checkIsRedirectToExpectedPage(String expectedUrl) {
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("URL does not match expected URL", expectedUrl, currentUrl);
        logger.info("Redirected to expected page: " + expectedUrl);
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
}
