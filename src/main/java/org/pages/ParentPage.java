package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ParentPage extends CommonActionsWithElements {
    protected String baseUrl = "https://parfums.ua/";
    protected Logger logger = Logger.getLogger(getClass());

    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
