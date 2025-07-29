package org.navigationTests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.baseTest.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class NavigationOnMenuInHeaderTest extends BaseTest {


    @Test
    @Parameters(method = "optionsForNavigation")
    public void TC03_testNavigationOnMenuInHeader(List<String> optionsForNavigation) {
        pageProvider.getHomePage().openHomePage().getHeaderElement()
                .checkMenu(optionsForNavigation);
    }

    public List<List<String>> optionsForNavigation() {
        return Arrays.asList(
                Arrays.asList("Makeup", "HealthAndCare", "Parfums", "CosmeticsForTheBody",
                        "BabyCare", "FaceCosmetics", "CosmeticsForNails", "EverythingForHome",
                        "HairCosmetics", "Parfums", "PharmacyCosmetics", "MenCosmetics", "Home"),
                Arrays.asList("MenCosmetics", "FaceCosmetics", "Home", "CosmeticsForNails", "MenCosmetics"),
                Arrays.asList("HairCosmetics", "CosmeticsForNails", "PharmacyCosmetics", "CosmeticsForNails", "HairCosmetics"),
                Arrays.asList("EverythingForHome", "PharmacyCosmetics", "CosmeticsForTheBody", "HealthAndCare", "Makeup")
        );
    }
}
