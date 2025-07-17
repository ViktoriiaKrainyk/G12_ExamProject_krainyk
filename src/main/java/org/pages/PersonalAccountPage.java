package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderElement;
import org.pages.elements.MessagePopUp;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonalAccountPage extends ParentPage {
    @FindBy(xpath = "//li[@data-tab='contact-info']")
    private WebElement contactInfoTab;

    @FindBy(xpath = "//li[@data-tab='address-book']")
    private WebElement addressBookTab;

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "surname")
    private WebElement inputSurname;

    @FindBy(id = "email")
    private WebElement inputEmail;

    @FindBy(id = "birthday")
    private WebElement inputBirthday;

    @FindBy(xpath = "//div[@class='calendar']")
    private WebElement calendar;

    @FindBy(xpath = "//span[@class='down-year']")
    private WebElement yearDropdown;

    @FindBy(xpath = "//span[@class='down-month']")
    private WebElement monthDropdown;

    @FindBy(xpath = "//button[@type='submit' and @class='button']")
    private WebElement saveButton;

    @FindBy (xpath = "//li[contains(@class,'exit')]")
    private WebElement exitButton;

    public PersonalAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected String getRelativeUrl() {
        return "/user";
    }

    public HeaderElement getHeaderElement() {
        return new HeaderElement(webDriver);
    }

    public MessagePopUp getMessagePopUp() {
        return new MessagePopUp(webDriver);
    }

    public PersonalAccountPage checkIsRedirectToPersonalAccountPage() {
        checkUrl();
        // TODO check some unique element on the home page
        return this;
    }

    public PersonalAccountPage checkTabsNames() {
        List<String> actualTabNames = getListOfTabNames();
        List<String> expectedTabNames = Arrays.asList("contact-info", "address-book", "wish-list", "history-order");
        Assert.assertEquals("Tabs names are not as expected", expectedTabNames, actualTabNames);
        logger.info("Tabs names are as expected: " + actualTabNames);
        return this;
    }

    public PersonalAccountPage checkIsExitButtonIsDisplayed() {
        checkIsElementDisplayed(exitButton);
        return this;
    }

    public PersonalAccountPage checkIsContactInfoTabIsActive() {
        checkOnlyOneTabIsActive();
        checkIsTabActive(contactInfoTab);
        return this;
    }

    public PersonalAccountPage checkUserContactInfo(String name, String surname, int date, int month, int year, String email) {
        checkValueInInputName(name);
        checkValueInInputSurname(surname);
        checkIsCalendarNotDisplayed();
        checkValueInInputBirthday(formatBirthday(date, month, year));
        checkValueInInputEmail(email);
        return this;
    }

    public PersonalAccountPage updateUserContactInfo(String name, String surname, int date, int month, int year, String dataSucessfullySavedMessage) {
        enterTextIntoInputName(name);
        enterTextIntoInputSurname(surname);
        checkIsCalendarNotDisplayed();
        setBirthdayValue(date, month, year);
        clickOnSaveButton();
        getMessagePopUp().checkMessagePopUpIsDisplayed()
                .checkMessageTextInMessagePopUp(dataSucessfullySavedMessage)
                .closeMessagePopUp();
        return this;
    }

    private PersonalAccountPage checkValueInInputName(String expectedValue) {
        checkValueInElement(inputName, expectedValue);
        return this;
    }

    private PersonalAccountPage checkValueInInputSurname(String expectedValue) {
        checkValueInElement(inputSurname, expectedValue);
        return this;
    }

    private PersonalAccountPage checkValueInInputBirthday(String expectedValue) {
        checkValueInElement(inputBirthday, expectedValue);
        return this;
    }

    private PersonalAccountPage checkValueInInputEmail(String expectedValue) {
        checkValueInElement(inputEmail, expectedValue);
        return this;
    }

    private PersonalAccountPage setBirthdayValue(int date, int month, int year) {
        clickOnElement(inputBirthday);
        checkIsCalendarDisplayed();
        clickOnElement(yearDropdown);
        selectValueInYearDropdown(year);
        clickOnElement(monthDropdown);
        selectValueInMonthDropdown(month);
        selectValueFromTheListOfDate(date);
        logger.info(String.format("Birthday value set to: %s.%s.%s", date, month, year));
        return this;
    }

    private PersonalAccountPage selectValueInYearDropdown(int year) {
        String yearXpath = "//span[@class='down-year']//div[@data-value='" + year + "']";
        selectValueFromTheList(yearXpath, "year", year);
        return this;
    }

    private PersonalAccountPage selectValueInMonthDropdown(int value) {
        int month = value - 1;
        String monthXpath = "//span[@class='down-month']//div[@data-value='" + month + "']";
        selectValueFromTheList(monthXpath, "month", month);
        return this;
    }

    private PersonalAccountPage selectValueFromTheListOfDate(int date) {
        String dateXpath = "//table//td/span[@class='day' and text()='" + date + "']";
        selectValueFromTheList(dateXpath, "date", date);
        return this;
    }

    private PersonalAccountPage checkIsCalendarDisplayed() {
        checkIsElementDisplayed(calendar);
        logger.info("Calendar is displayed as expected");
        return this;
    }

    private PersonalAccountPage checkIsCalendarNotDisplayed() {
        checkIsElementNotDisplayed(calendar);
        logger.info("Calendar is not displayed as expected");
        return this;
    }

    private PersonalAccountPage enterTextIntoInputName(String text) {
        clearAndEnterTextToElement(inputName, text);
        logger.info("Entered text into input name: " + text);
        return this;
    }

    private PersonalAccountPage enterTextIntoInputSurname(String text) {
        clearAndEnterTextToElement(inputSurname, text);
        logger.info("Entered text into input surname: " + text);
        return this;
    }

    private PersonalAccountPage clickOnSaveButton() {
        clickOnElement(saveButton);
        logger.info("Save button was clicked");
        return this;
    }

    private List<String> getListOfTabNames() {
        return webDriver.findElements(By.xpath("//ul[@class='private-office__tabs']//li"))
                .stream()
                .map(el -> el.getAttribute("data-tab"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private List<WebElement> getListOfTabs() {
        return webDriver.findElements(
                By.xpath("//ul[@class='private-office__tabs']//li"));
    }

    private void checkIsTabActive(WebElement tab) {
        Assert.assertTrue(getElementName(tab) + " is not active, but it should be", isActiveElement(tab));
        logger.info(getElementName(tab) + " is active as expected");
    }

    private PersonalAccountPage checkOnlyOneTabIsActive() {
        List<WebElement> tabs = getListOfTabs();
        int activeTabsCount = Math.toIntExact(tabs.stream()
                .filter(this::isActiveElement)
                .count());

        Assert.assertEquals("Number of active tabs is not as expected", 1, activeTabsCount);
        logger.info("Number of active tabs: " + activeTabsCount);
        return this;
    }

    private String getElementName(WebElement webElement) {
        String elementName;
        try {
            elementName = webElement.getText();
            logger.info("Element name: " + elementName);
        } catch (Exception e) {
            elementName = "Element";
            logger.info("Element name is not found, using \"Element\" name instead");
        }
        return elementName;
    }

    private String formatBirthday(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

}
