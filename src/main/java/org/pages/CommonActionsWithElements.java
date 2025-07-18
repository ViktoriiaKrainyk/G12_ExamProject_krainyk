package org.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    private Logger logger = Logger.getLogger(getClass());
    protected WebDriverWait webDriverWait5, webDriverWait10, webDriverWait15, webDriverWait20;

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this); // Initialize the elements described in this class in FindBy annotations
        webDriverWait5 =  new WebDriverWait(webDriver, Duration.ofSeconds(5));
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        webDriverWait20 = new WebDriverWait(webDriver, Duration.ofSeconds(15));
    }

    protected void clearAndEnterTextToElement(WebElement webElement, String text) {
        try {
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was entered in element '" + getElementName(webElement) + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait10
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            String elementName = getElementName(webElement);
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement, String elementName) {
        try {
            webDriverWait10
                    .withMessage("Element is not clickable: " + webElement)
                    .until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
            logger.info(elementName + " element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed: " + getElementName(webElement));
            } else {
                logger.info("Element is not displayed: " + getElementName(webElement));
            }
            return state;
        } catch (Exception e) {
            logger.info("Element is not found, so it is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement webElement, String elementName) {
        try {
            boolean state = webElement.isDisplayed();
            if (state) {
                logger.info("Element is displayed: " + elementName);
            } else {
                logger.info("Element is not displayed: " + elementName);
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

    protected void checkIsElementDisplayed(WebElement webElement, String elementName) {
        Assert.assertTrue("Element is not displayed", isElementDisplayed(webElement, elementName));
        logger.info("Element is displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement) {
        Assert.assertFalse("Element is displayed, but it should not be", isElementDisplayed(webElement));
        logger.info("Element is not displayed as expected");
    }

    protected void checkIsElementNotDisplayed(WebElement webElement, String elementname) {
        Assert.assertFalse("Element is displayed, but it should not be", isElementDisplayed(webElement, elementname));
        logger.info("Element is not displayed as expected");
    }

    protected void checkTextInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getText();
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    protected void checkPlaceholderInElement(WebElement webElement, String expectedText) {
        String actualText = webElement.getAttribute("placeholder");
        Assert.assertEquals("Text in element does not match expected text", expectedText, actualText);
        logger.info("Text in element matches expected text: " + expectedText);
    }

    protected String getValueFromElement(WebElement webElement) {
        try {
            String value = webElement.getAttribute("value");
            logger.info("Value from element '" + getElementName(webElement) + "' is " + value);
            return value != null ? value : "";
        } catch (Exception e) {
            printErrorAndStopTest(e);
            return "";
        }
    }

    protected void selectValueFromTheList(String elementXpath, WebElement webElement, int value) {
        try {
            List<WebElement> options = webDriver.findElements(By.xpath(elementXpath));
            if (options.isEmpty()) {
                String message = "Value '" + value + "' is not found in the element '" + getElementName(webElement) + "'" ;
                logger.error(message);
                throw new NoSuchElementException(message);
            }
            options.get(0).click();
            logger.info("Value '" + value + "' was selected in the element '" + getElementName(webElement) + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueFromTheList(String elementXpath, String element, int value) {
        try {
            List<WebElement> options = webDriver.findElements(By.xpath(elementXpath));
            if (options.isEmpty()) {
                String message = "Value '" + value + "' is not found in the element '" + element + "'" ;
                logger.error(message);
                throw new NoSuchElementException(message);
            }
            options.get(0).click();
            logger.info("Value '" + value + "' was selected in the element '" + element + "'");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void checkValueInElement(WebElement webElement, String expectedValue) {
        Assert.assertEquals("Value in element '" + getElementName(webElement) + "' does not match expected value", expectedValue, getValueFromElement(webElement));
        logger.info("Value in element '" + getElementName(webElement) + "' matches expected value: " + expectedValue);
    }

    protected boolean isActiveElement(WebElement element) {
        try {
            boolean state = element.getAttribute("class").contains("active");
            if (state) {
                logger.info("Element is active");
            } else {
                logger.info("Element is not active");
            }
            return state;
        } catch (Exception e) {
            logger.info("Error while checking if tab is active: " + e.getMessage());
            return false;
        }
    }

    protected boolean isActiveElement(WebElement element, String elementName) {
        try {
            boolean state = element.getAttribute("class").contains("active");
            if (state) {
                logger.info("Element is active: " + elementName);
            } else {
                logger.info("Element is not active: " + elementName);
            }
            return state;
        } catch (Exception e) {
            logger.info("Error while checking if tab is active: " + e.getMessage());
            return false;
        }
    }

    private String getElementName(WebElement webElement) {
        try {
            return webElement.getAccessibleName();
        } catch (Exception e) {
            return "";
        }
    }

    private void printErrorAndStopTest(Exception e) {
        logger.error("Error while working with element: " + e.getMessage());
        Assert.fail("Error while working with element: " + e.getMessage());
    }
}
