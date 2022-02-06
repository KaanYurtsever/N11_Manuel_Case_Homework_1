package com.n11.pages;

import com.n11.utilities.Driver;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.n11.utilities.BrowserUtils.hover;

public class StoresPage extends BasePage{

    static WebDriver driver = Driver.get();

    //All stores tab
    @FindBy(xpath = "//*[@id=\"contentSellerList\"]/div/div[2]/div/div/ul/li[4]")
    public WebElement allStores;

    //Text of showing the opening of the stores page
    @FindBy(xpath = "//*[@class = \"cont\"]/h1")
    public WebElement storesCont;

    //Text of showing the scrolling to the all stores
    @FindBy(xpath = "//*[@class = \"tabPanel allSellers\"]/h2")
    public WebElement allStoresTxt;

    //List of all stores' letters
    @FindBy(xpath = "//*[@class=\"letters\"]/span")
    public List<WebElement> storeLetters;

    //Each stores' letter
    public static WebElement getStoreLetters(int StoreLetter) {
        return driver.findElement(By.xpath("//*[@id=\"contentSellerList\"]/div/div[2]/div/div[2]/div[4]/div[1]/span[" + StoreLetter + "]"));}

    //List of store names in each letter
    public static List<WebElement> getStoreNames() {
        return driver.findElements(By.xpath("//*[@id=\"contentSellerList\"]/div/div[2]/div/div[2]/div[4]/div[2]/ul"));}

    //Seller name which is exported from Excel
    @FindBy(className = "sallerName")
    public WebElement sellerName;

    //Store comments button
    @FindBy(xpath = "//*[@id=\"contentSellerShop\"]/div/section[2]/div[2]/div[1]/ul/li[2]/div/h3/a")
    public WebElement storeComments;

    //Store review which is show that the store get how many comments
    @FindBy(className = "selectedReview")
    public WebElement storesReview;


    //List of store names for using in Excel
    ArrayList<String[]> allStoreNames = new ArrayList<>();
    //Name of Excel file
    String excelFileName = "allStoreNames.xls";
    HSSFSheet sheet;
    HSSFWorkbook wb = new HSSFWorkbook();
    String sheetName;
    String storeName;

    public void goAllStores(){
        //Click all stores button
        allStores.click();
    }

    public void getAllStoreNames() throws IOException, InterruptedException {
        //Count all store letters for using in a loop
        for (int i = 0; i < storeLetters.size(); i++) {
            //To give a store letter name to sheet in Excel
            sheetName = getStoreLetters(i + 1).getText().toLowerCase(Locale.ROOT);
            //Create sheets in workbook
            sheet = wb.createSheet(sheetName);
            //Click each store letter by one by
            getStoreLetters(i + 1).click();
            Thread.sleep(750);
            //Add all store names to a list for using in Excel sheet
            allStoreNames.add(getStoreNames().get(0).getText().split("\n"));
            //Count all store names in each letter for using in a loop
            for (int j = 0; j < allStoreNames.get(i).length; j++) {
                //Set values each cell which is store names
                sheet.createRow(j).createCell(0).setCellValue(allStoreNames.get(i)[j]);
            }
        }
        FileOutputStream fileOut = new FileOutputStream(excelFileName);

        //Write this workbook to a stream
        wb.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }

    public void getStoreNameExcel() throws IOException {
        FileInputStream fileOut = new FileInputStream(excelFileName);
        //Get wanted store name by reading from Excel file and set a variable to it
        storeName = wb.getSheetAt(21).getRow(1).getCell(0).toString();
        fileOut.close();

    }
    public void goToStorePage() {
        //Scrolls to search field
        hover(searchField);
        //Writes exported store name
        searchField.sendKeys(storeName);
        //Goes to store page
        searchField.sendKeys(Keys.ENTER);
        //Clicks store name
        sellerName.click();
        //Clicks store comments
        storeComments.click();
    }
}
