package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public BasePage(){ PageFactory.initElements(Init.getDriver(), this); }

    public void fillField(WebElement element, String text){
        element.click();
        for (int i = 0; i < 7; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(text);
    }

    public void checkBox(WebElement checkbox){
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 60, 2000);
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        if(!(checkbox.isSelected())){
            new WebDriverWait(Init.getDriver(),60).until(ExpectedConditions.elementToBeClickable(checkbox)).click();
        }
        scrollToElement(checkbox);
    }

    public void scrollToElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Init.getDriver();
        Assert.assertNotNull(element);
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public boolean isElementPresented(WebElement element) {
        try {
            Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        finally {
            Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }

    public void click(WebElement element) {
        Wait<WebDriver> wait = new WebDriverWait(Init.getDriver(), 60, 2000);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void compareText(WebElement element, String expected) {
        isElementPresented(element);
        String actual = element.getText();
        Assert.assertTrue(("Искомого текста нет: " + expected + " вместо него " + actual), actual.equals(expected));
        System.out.println("Искомый текст есть: " + expected);
    }

    public void waitPageLoaded() {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30);
        wait.ignoring(NoSuchElementException.class).until((ExpectedCondition<Boolean>) driver -> !isPresent(By.xpath("//*[@class='helpers-params loading']")));
    }

    public boolean isPresent(By locator) {
        try {
            Init.getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return Init.getDriver().findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        }
    }


}
