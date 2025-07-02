package org.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.pages.elements.HeaderElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonalAccountPage extends ParentPage {
    @FindBy(xpath = "//li[contains(text(),'Контактна інформація')]")
    private WebElement contactInfoTab;

    @FindBy(xpath = "//li[contains(text(),'Адресна книга')]")
    private WebElement addressBookTab;

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

    private boolean isActiveTab(WebElement tab) {
        try {
            boolean state = tab.getAttribute("class").contains("active");
            if (state) {
                logger.info(getElementName(tab) + " tab is active");
            } else {
                logger.info(getElementName(tab) + " tab is not active");
            }
            return state;
        } catch (Exception e) {
            logger.info("Error while checking if tab is active: " + e.getMessage());
            return false;
        }
    }

    private void checkIsTabActive(WebElement tab) {
        Assert.assertTrue(getElementName(tab) + " is not active, but it should be", isActiveTab(tab));
        logger.info(getElementName(tab) + " is active as expected");
    }

    private List<String> getListOfTabNames() {
        return webDriver.findElements(
                        By.xpath("//ul[@class='private-office__tabs']//li"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    private List<WebElement> getListOfTabs() {
        return webDriver.findElements(
                By.xpath("//ul[@class='private-office__tabs']//li"));
    }

    public PersonalAccountPage checkTabsNames() {
        List<String> actualTabNames = getListOfTabNames();
        List<String> expectedTabNames = Arrays.asList("Контактна інформація", "Адресна книга", "Список бажань", "Історія замовлень", "Вихід");
        Assert.assertEquals("Tabs names are not as expected", expectedTabNames, actualTabNames);
        logger.info("Tabs names are as expected: " + actualTabNames);
        return this;
    }

    public PersonalAccountPage checkIsContactInfoTabIsActive() {
        checkOnlyOneTabIsActive();
        checkIsTabActive(contactInfoTab);
        return this;
    }

    public PersonalAccountPage checkOnlyOneTabIsActive() {
        List<WebElement> tabs = getListOfTabs();
        int activeTabsCount = Math.toIntExact(tabs.stream()
                .filter(this::isActiveTab)
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

}
