package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }

    public PersonalAccountPage getPersonalAccountPage() {
        return new PersonalAccountPage(webDriver);
    }

    public ParfumsPage getParfumsPage() {
        return new ParfumsPage(webDriver);
    }

    public MakeUpPage getMakeUpPage() {
        return new MakeUpPage(webDriver);
    }

    public HairCosmeticsPage getHairCosmeticsPage() {
        return new HairCosmeticsPage(webDriver);
    }

    public FaceCosmeticsPage getFaceCosmeticsPage() {
        return new FaceCosmeticsPage(webDriver);
    }

    public PharmacyCosmeticsMenu getPharmacyCosmeticsMenu() {
        return new PharmacyCosmeticsMenu(webDriver);
    }

    public CosmeticsForTheBodyPage getCosmeticsForTheBodyPage() {
        return new CosmeticsForTheBodyPage(webDriver);
    }

    public CosmeticsForNailsPage getCosmeticsForNailsPage() {
        return new CosmeticsForNailsPage(webDriver);
    }

    public EverythingForHomePage getEverythingForHomePage() {
        return new EverythingForHomePage(webDriver);
    }

    public BabyCarePage getBabyCarePage() {
        return new BabyCarePage(webDriver);
    }

    public MenCosmeticsPage getMenCosmeticsPage() {
        return new MenCosmeticsPage(webDriver);
    }

    public HealthAndCarePage getHealthAndCarePage() {
        return new HealthAndCarePage(webDriver);
    }

    public FavoritePage getFavoritePage() {
        return new FavoritePage(webDriver);
    }

    public SalePage getSalePage() {
        return new SalePage(webDriver);
    }

    public ProductPage getProductPage() {
        return new ProductPage(webDriver);
    }
}
