package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Поиск по Википедии')]",
            SEARCH_INPUT = "//*[contains(@text, 'Поиск')]",
            SEARCH_RESULT = "//*[@text='{SUBSTRING}']";



    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода", 15);

        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно найти поле ввода", 15);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Невозможно найти поле ввода", 15);
    }

    public void sendKeys(String substring, String text){
        this.waitForElementAndSendKeys(By.xpath(String.format("//*[@resource-id='%s']", substring)),
                text, "Неудалось найти", 15);
    }

    public void click(String type, String substring){
        this.waitForElementAndClick(By.xpath(String.format("//*[@%s='%s']", type, substring)),
                "Неудалось найти", 15);
    }

    public void clickAndHold(String type, String substring){
        this.waitForElementAndClickAndHold(By.xpath(String.format("//*[@%s='%s']", type, substring)),
                "Неудалось найти", 15);
    }

    public void clearText(String substring){
        this.waitForElementAndClear(By.xpath(String.format("//*[@resource-id='%s']", substring)),
                "Неудалось найти" + substring, 15);
    }

    private static String getResultSearchInitElement(String substring){
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchInitElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Невозможно найти" + substring, 15);
    }
}