package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderElement;
import org.pages.elements.MessagePopUp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.apache.commons.io.FilenameUtils.equalsNormalized;

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
    private WebElement yearList;

    @FindBy(xpath = "//span[@class='down-month']")
    private WebElement monthList;

    @FindBy(xpath = "//button[@type='submit' and @class='button']")
    private WebElement saveButton;

    @FindBy (xpath = "//a[@href='/ua/user/logout/']")
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
        checkTabsNames();
        checkIsExitButtonIsDisplayed();
        logger.info("The AccountPage is opened");
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
        checkIsElementDisplayed(exitButton, "Exit button");
        return this;
    }

    public PersonalAccountPage checkIsContactInfoTabIsActive() {
        checkOnlyOneTabIsActive();
        checkIsTabActive(contactInfoTab, "Contact info tab");
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

    public HomePage updateUserContactInfoIfNeeded(String name, String surname, int dayOfBirth, int monthOfBirth, int yearOfBirth, String dataSucessfullySavedMessage) {
        List<Runnable> updateActions = new ArrayList<>();

        // Перевірка імені
        if (!equalsNormalized(getValueFromElement(inputName), name)) {
            updateActions.add(() -> enterTextIntoInputName(name));
        }

        // Перевірка прізвища
        if (!equalsNormalized(getValueFromElement(inputSurname), surname)) {
            updateActions.add(() -> enterTextIntoInputSurname(surname));
        }

        // Перевірка дати народження
        String currentBirthDate = getValueFromElement(inputBirthday); // метод, що повертає дату народження з форми
        String expectedBirthDate = formatBirthday(dayOfBirth, monthOfBirth, yearOfBirth);
        if (!Objects.equals(currentBirthDate, expectedBirthDate)) {
            updateActions.add(() -> {
                checkIsCalendarNotDisplayed();
                setBirthdayValue(dayOfBirth, monthOfBirth, yearOfBirth);
            });
        }

        // Якщо були зміни — виконуємо апдейт
        if (!updateActions.isEmpty()) {
            updateActions.forEach(Runnable::run);
            clickOnSaveButton();
            getMessagePopUp()
                    .checkMessagePopUpIsDisplayed()
                    .checkMessageTextInMessagePopUp(dataSucessfullySavedMessage)
                    .closeMessagePopUp();
            clickOnExitButton();
            logger.info("User was log out with updated data.");

        } else {
            logger.info("No updates needed – all fields match.");
            clickOnExitButton();
            logger.info("User was log out without changes.");
        }
        return new HomePage(webDriver);
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
        clickOnElement(yearList, "Year list");
        selectValueInYearDropdown(year);
        clickOnElement(monthList, "Month list");
        selectValueInMonthDropdown(month);
        selectValueFromTheListOfDate(date);
        logger.info(String.format("Birthday value set to: %s.%s.%s", date, month, year));
        return this;
    }

    private PersonalAccountPage selectValueInYearDropdown(int year) {
        String yearXpath = "//span[@class='down-year']//div[@data-value='" + year + "']";
        selectValueFromTheList(yearXpath, "Year List", year);
        return this;
    }

    private PersonalAccountPage selectValueInMonthDropdown(int value) {
        int month = value - 1;
        String monthXpath = "//span[@class='down-month']//div[@data-value='" + month + "']";
        selectValueFromTheList(monthXpath, "Month List", month);
        return this;
    }

    private PersonalAccountPage selectValueFromTheListOfDate(int date) {
        String dateXpath = "//table//td/span[@class='day' and text()='" + date + "']";
        selectValueFromTheList(dateXpath, inputBirthday, date);
        return this;
    }

    private PersonalAccountPage checkIsCalendarDisplayed() {
        checkIsElementDisplayed(calendar, "Calendar");
        return this;
    }

    private PersonalAccountPage checkIsCalendarNotDisplayed() {
        checkIsElementNotDisplayed(calendar, "Calendar");
        return this;
    }

    private PersonalAccountPage enterTextIntoInputName(String text) {
        clearAndEnterTextToElement(inputName, text);
        return this;
    }

    private PersonalAccountPage enterTextIntoInputSurname(String text) {
        clearAndEnterTextToElement(inputSurname, text);
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

    private void checkIsTabActive(WebElement tab, String elementName) {
        Assert.assertTrue("Tab is not active, but it should be", isActiveElement(tab, elementName));
        logger.info("Tab is active as expected");
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

    private String formatBirthday(int day, int month, int year) {
        return String.format("%02d.%02d.%d", day, month, year);
    }

    private HomePage clickOnExitButton() {
        clickOnElement(exitButton, "Exit button");
        logger.info("Exit button was clicked");
        return new HomePage(webDriver);
    }
}
