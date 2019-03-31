package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//ul[@class='main-menu']//li")
    public List<WebElement> menu;

    @FindBy(xpath = "//a[@href='/retail/mortgageloans/calculator/']")
    public WebElement calc;

    @FindBy(xpath = "//*[contains(text(),'Да')]")
    public WebElement button;

    @Step("подтвержден город")
    public void selectButton(){
        click(button);
    }

    @Step("выбран пункт меню - {0}")
    public void selectMainMenu(String name){
        for(WebElement item : menu){
            if(item.getText().equalsIgnoreCase(name)) {
                new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
        Assert.fail("Не найден пункт меню " + menu);
    }

    @Step("выбран подпункт меню \"Ипотечный калькулятор\"")
    public void chooseCalc(){
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 30, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(calc));
        calc.click();
    }
}
