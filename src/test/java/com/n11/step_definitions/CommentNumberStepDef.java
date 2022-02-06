package com.n11.step_definitions;


import com.n11.pages.BasePage;
import com.n11.pages.StoresPage;
import com.n11.utilities.ConfigurationReader;
import com.n11.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class CommentNumberStepDef {

    WebDriver driver = Driver.get();
    BasePage basePage = new BasePage();
    StoresPage storesPage = new StoresPage();


    @Given("User opens the browser and goes to the stores page from home page")
    public void user_opens_the_browser_and_goes_to_the_stores_page_from_home_page() {
        //Go to the given url
        driver.get(ConfigurationReader.get("url"));
        //Confirmation of expected url equals current url
        Assert.assertEquals(ConfigurationReader.get("url"),driver.getCurrentUrl());
        //Close cookies on the page
        basePage.closeCookies();
        //Go to stores page by clicking header on the top
        basePage.goToStoresPage();
        //Confirmation of stores page is opened
        Assert.assertTrue(storesPage.storesCont.isDisplayed());
        //Go to all stores tab on the stores page
        storesPage.goAllStores();
        //Confirmation of all stores page is opened
        Assert.assertTrue(storesPage.allStoresTxt.getText().contains("Tüm Mağazalar"));
    }

    @When("All stores written an excel file")
    public void all_stores_written_an_excel_file() throws IOException, InterruptedException {
        //Get all store names and import to an Excel file
        storesPage.getAllStoreNames();
    }

    @And("User choose the store which was wanted")
    public void user_choose_the_store_which_wanted() throws IOException {
        //Get store name which is wanted and export from an Excel file
        storesPage.getStoreNameExcel();
    }

    @Then("User sees comment count of store")
    public void user_sees_comment_count_of_store() {
        //Go to the wanted store page and check comment count
        storesPage.goToStorePage();
        //Confirmation of the store's comment count is displayed
        Assert.assertTrue(storesPage.storesReview.getText().contains("yorum"));
    }
}
