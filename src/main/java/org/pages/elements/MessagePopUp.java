package org.pages.elements;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.CommonActionsWithElements;
import org.pages.PersonalAccountPage;

public class MessagePopUp extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());
    @FindBy(xpath = "//div[@id='popup']")
    private WebElement messagePopUp;

    @FindBy(xpath = "//div[@id='popup__window']//h2")
    private WebElement successText;

    @FindBy(xpath = "//div[@id='popup__window']//p")
    private WebElement messageText;

    @FindBy(xpath = "//div[@id='popup']//div[contains(@class,'popup-close')] ")
    private WebElement closePopUpButton;

    public MessagePopUp(WebDriver webDriver) {
        super(webDriver);
    }

    public MessagePopUp checkMessagePopUpIsDisplayed() {
        webDriverWait20.until(ExpectedConditions.visibilityOf(messagePopUp));
        checkIsElementDisplayed(messagePopUp, "Message popUp");
        return this;
    }

    public MessagePopUp checkMessageTextInMessagePopUp(String expectedText) {
        String actualText = successText.getText() + " " + messageText.getText();
        Assert.assertEquals("Text in Message pop-up does not match expected text", expectedText, actualText);
        logger.info("Text in Message pop-up matches expected text: " + expectedText);
        return this;
    }

    public PersonalAccountPage closeMessagePopUp() {
        clickOnElement(closePopUpButton, "Close popUp button");
        webDriverWait15.until(ExpectedConditions.invisibilityOf(messagePopUp));
        logger.info("PopUp closed successfully");
        return new PersonalAccountPage(webDriver);
    }
}
