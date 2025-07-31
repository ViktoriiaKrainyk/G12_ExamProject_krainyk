package org.pages.elements;

import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.pages.*;

import java.util.List;

public class HeaderElement extends CommonActionsWithElements {
    Logger logger = Logger.getLogger(getClass());
    PageProvider pageProvider = new PageProvider(webDriver);

    @FindBy(xpath = "//div[@id='profile_desktop']//button[contains(@class,'profile')]")
    private WebElement loginToAccountButton;

    @FindBy(xpath = "//a[@id='profile_desktop']//button[contains(@class,'profile')]")
    private WebElement accountButton;

    @FindBy(xpath = "//button[@id='favorites']")
    private WebElement favoriteButton;

    @FindBy(xpath = "//button[@id='favorites']//span[@class='favourite-count']")
    private WebElement favoritesCount;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/parfums/']")
    private WebElement parfumsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/makeup/']")
    private WebElement makeupMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/hair-cosmetics/']")
    private WebElement hairCosmeticsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/cosmetics-for-the-face/']")
    private WebElement faceCosmeticsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/aptechnaya-kosmetika/']")
    private WebElement pharmacyCosmeticsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/cosmetics-for-the-body/']")
    private WebElement cosmeticsToTheBodyMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/cosmetics-for-nails/']")
    private WebElement cosmeticsForNailsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/bytovaya_khimiya/']")
    private WebElement everythingForHomeMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/baby-care/']")
    private WebElement babyCareMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/mugskaya-kosmetika/']")
    private WebElement menCosmeticsMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu_link') and @href='/ua/category/health-and-care/']")
    private WebElement healthAndCareMenu;

    @FindBy(xpath = "//a[@class='nav_link sale']")
    private WebElement saleMenu;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    public AuthForm getAuthForm() {
        return new AuthForm(webDriver);
    }

    public HeaderElement checkLoginToAccountButtonIsDisplayed() {
        checkIsElementDisplayed(loginToAccountButton);
        return this;
    }

    public HeaderElement checkLoginToAccountButtonIsNotDisplayed() {
        checkIsElementNotDisplayed(loginToAccountButton);
        return this;
    }

    public HeaderElement checkTextInLoginToAccountButton(String expectedText ) {
        checkTextInElement(loginToAccountButton, expectedText);
        return this;
    }

    public HeaderElement clickOnLoginToAccountButton() {
        clickOnElement(loginToAccountButton);
        return this;
    }

    public HeaderElement checkAccountButtonIsDisplayed() {
        checkIsElementDisplayed(accountButton);
        return this;
    }

    public HeaderElement checkTextInAccountButton(String expectedText) {
        checkTextInElement(accountButton, expectedText);
        return this;
    }

    public boolean isAccountButtonForLoggedUser() {
       return isElementDisplayed(accountButton);
    }

    public HeaderElement checkFavoritesCountIsDisplayed() {
        checkIsElementDisplayed(favoritesCount, "Favorite count");
        return this;
    }

    public HeaderElement checkFavoritesCountIsNotDisplayed() {
        checkIsElementNotDisplayed(favoritesCount);
        return this;
    }

    public HeaderElement checkFavoritesCount(int expectedCount) {
        checkTextInElement(favoritesCount, "(" + expectedCount + ")");
        return this;
    }

    public int getFavoritesCount() {
        String text = favoritesCount.getText().trim(); // очікується формат типу "(3)"
        int count;
        if (text.matches("\\(\\d+\\)")) {
            count = Integer.parseInt(text.replaceAll("[()]", ""));
            logger.info("Favorites count is: " + text);
            return count;
        } else {
            throw new IllegalStateException("Unexpected favorites count format: '" + text + "'");
        }
    }


    public FavoritePage clickOnFavoriteButton() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                WebElement freshFavoriteButton = webDriverWait10.until(ExpectedConditions.elementToBeClickable(By.id("favorites")));
                freshFavoriteButton.click();
                logger.info("Click on Favorite Button");
                return new FavoritePage(webDriver);
            } catch (StaleElementReferenceException e) {
                attempts++;
                logger.warn("StaleElementReferenceException on attempt " + attempts);
            }
        }
        throw new RuntimeException("Failed to click on Favorite Button after several attempts.");
    }


    public HeaderElement checkLoginSuccess (String accountButtonText) {
                checkAccountButtonIsDisplayed();
                checkTextInAccountButton(accountButtonText);
                checkLoginToAccountButtonIsNotDisplayed();
                checkFavoritesCountIsDisplayed();;
        return this;
    }

    public HeaderElement checkLoginSuccessWithFavoritesCount(int expectedFavoritesCount) {
        checkAccountButtonIsDisplayed();
        checkLoginToAccountButtonIsNotDisplayed();
        checkFavoritesCountIsDisplayed();
        checkFavoritesCount(expectedFavoritesCount);
        return this;
    }

    public PersonalAccountPage clickOnAccountButton() {
        clickOnElement(accountButton);
        logger.info("Click on Account Button");
        return new PersonalAccountPage(webDriver);
    }

    public ParfumsPage navigateToParfumsMenuAndCheck() {
        clickOnElement(parfumsMenu);
        ParfumsPage parfumsPage =
                pageProvider.getParfumsPage()
                        .checkIsRedirectToParfumsPage();
        return parfumsPage;
    }

    public MakeUpPage navigateToMakeupMenuAndCheck() {
        clickOnElement(makeupMenu);
        MakeUpPage makeUpPage =
                pageProvider.getMakeUpPage()
                        .checkIsRedirectToMakeupPage();
        return makeUpPage;
    }

    public HairCosmeticsPage navigateToHairCosmeticsMenuAndCheck() {
        clickOnElement(hairCosmeticsMenu);
        HairCosmeticsPage hairCosmeticsPage =
                pageProvider.getHairCosmeticsPage()
                        .checkIsRedirectToHairCosmeticsPage();
        return hairCosmeticsPage;
    }

    public FaceCosmeticsPage navigateToFaceCosmeticsMenuAndCheck() {
        clickOnElement(faceCosmeticsMenu);
        FaceCosmeticsPage faceCosmeticsPage =
                pageProvider.getFaceCosmeticsPage()
                        .checkIsRedirectToFaceCosmeticsPage();
        return faceCosmeticsPage;
    }

    public PharmacyCosmeticsMenu navigateToPharmacyCosmeticsMenuAndCheck() {
        clickOnElement(pharmacyCosmeticsMenu);
        PharmacyCosmeticsMenu pharmacyCosmeticsMenu =
                pageProvider.getPharmacyCosmeticsMenu()
                        .checkIsRedirectToPharmacyCosmeticsMenu();
        return pharmacyCosmeticsMenu;
    }

    public CosmeticsForTheBodyPage navigateToCosmeticsForTheBodyMenuAndCheck() {
        clickOnElement(cosmeticsToTheBodyMenu);
        CosmeticsForTheBodyPage cosmeticsForTheBodyPage =
                pageProvider.getCosmeticsForTheBodyPage()
                        .checkIsRedirectToCosmeticsForTheBodyPage();
        return cosmeticsForTheBodyPage;
    }

    public CosmeticsForNailsPage navigateToCosmeticsForNailsMenuAndCheck() {
        clickOnElement(cosmeticsForNailsMenu);
        CosmeticsForNailsPage cosmeticsForNailsPage =
                pageProvider.getCosmeticsForNailsPage()
                        .checkIsRedirectToCosmeticsForNailsPage();
        return cosmeticsForNailsPage;
    }

    public EverythingForHomePage navigateToEverythingForHomeMenuAndCheck() {
        clickOnElement(everythingForHomeMenu);
        EverythingForHomePage everythingForHomePage =
                pageProvider.getEverythingForHomePage()
                        .checkIsRedirectToEverythingForHomePage();
        return everythingForHomePage;
    }

    public BabyCarePage navigateToBabyCareMenuAndCheck() {
        clickOnElement(babyCareMenu);
        BabyCarePage babyCarePage =
                pageProvider.getBabyCarePage()
                        .checkIsRedirectToBabyCarePage();
        return babyCarePage;
    }

    public MenCosmeticsPage navigateToMenCosmeticsMenuAndCheck() {
        clickOnElement(menCosmeticsMenu);
        MenCosmeticsPage menCosmeticsPage =
                pageProvider.getMenCosmeticsPage()
                        .checkIsRedirectToMenCosmeticsPage();
        return menCosmeticsPage;
    }

    public HealthAndCarePage navigateToHealthAndCareMenuAndCheck() {
        clickOnElement(healthAndCareMenu);
        HealthAndCarePage healthAndCarePage =
                pageProvider.getHealthAndCarePage()
                        .checkIsRedirectToHealthAndCarePage();
        return healthAndCarePage;
    }

    public SalePage navigateToSaleMenuAndCheck() {
        clickOnElement(saleMenu);
        SalePage salePage =
                pageProvider.getSalePage()
                        .checkIsRedirectToSalePage();
        return salePage;
    }

    public void checkMenu(List<String> optionsForNavigation){
        for (String menuItem : optionsForNavigation) {
            switch (menuItem) {
                case "Parfums":
                    pageProvider.getParfumsPage().getHeaderElement().navigateToParfumsMenuAndCheck();
                    break;
                case "Makeup":
                    pageProvider.getMakeUpPage().getHeaderElement().navigateToMakeupMenuAndCheck();
                    break;
                case "HairCosmetics":
                    pageProvider.getHairCosmeticsPage().getHeaderElement().navigateToHairCosmeticsMenuAndCheck();
                    break;
                case "FaceCosmetics":
                    pageProvider.getFaceCosmeticsPage().getHeaderElement().navigateToFaceCosmeticsMenuAndCheck();
                    break;
                case "PharmacyCosmetics":
                    pageProvider.getPharmacyCosmeticsMenu().getHeaderElement().navigateToPharmacyCosmeticsMenuAndCheck();
                    break;
                case "CosmeticsForTheBody":
                    pageProvider.getCosmeticsForTheBodyPage().getHeaderElement().navigateToCosmeticsForTheBodyMenuAndCheck();
                    break;
                case "CosmeticsForNails":
                    pageProvider.getCosmeticsForNailsPage().getHeaderElement().navigateToCosmeticsForNailsMenuAndCheck();
                    break;
                case "EverythingForHome":
                    pageProvider.getEverythingForHomePage().getHeaderElement().navigateToEverythingForHomeMenuAndCheck();
                    break;
                case "BabyCare":
                    pageProvider.getBabyCarePage().getHeaderElement().navigateToBabyCareMenuAndCheck();
                    break;
                case "MenCosmetics":
                    pageProvider.getMenCosmeticsPage().getHeaderElement().navigateToMenCosmeticsMenuAndCheck();
                    break;
                case "HealthAndCare":
                    pageProvider.getHealthAndCarePage().getHeaderElement().navigateToHealthAndCareMenuAndCheck();
                    break;
                case "Home":
                    pageProvider.getHomePage().openHomePage()
                            .checkIsRedirectToHomePage();
                    break;
                case "Sale":
                    pageProvider.getSalePage().getHeaderElement().navigateToSaleMenuAndCheck();
                    break;
                default:
                    throw new IllegalArgumentException("!!!UNKNOWN MENU ITEM: " + menuItem);
            }
        }
    }
}
