package com.n11.pages;

import com.n11.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    //Close location pop-up button
    @FindBy(id = "myLocation-close-info")
    public WebElement locationOkBtn;

    //Close cookie button
    @FindBy(xpath = "//*[@id=\"cookieUsagePopIn\"]/span")
    public WebElement cookieBtn;

    //Stores button on the header
    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div[3]/nav/ul/li[5]/span")
    public WebElement storesBtn;

    //See stores button on the stores tab
    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div[3]/nav/ul/li[5]/div/div/a[1]")
    public WebElement seeStoresBtn;

    //Search field
    @FindBy(id = "searchData")
    public static WebElement searchField;


    public void goToStoresPage(){
        Actions action = new Actions(Driver.get());
        //Move to stores button
        action.moveToElement(storesBtn).perform();
        //Click see stores button
        seeStoresBtn.click();
    }

    public void closeCookies(){
        //Close location pop-up
        locationOkBtn.click();
        //Close cookies pop-up
        cookieBtn.click();
    }

}
