package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Init;

import java.util.List;

public class CalculatorPage extends BasePage {

    @FindBy(xpath = "//h1[contains(text(), 'Ипотечный калькулятор')]")
    public WebElement header;

    @FindBy(xpath = "//ul[@aria-labelledby=\"form_city-button\"]//li")
    public List<WebElement> cityList;

    @FindBy(xpath = "//label[@class=\"checkbox-block__label\"]")
    public WebElement checkBox;

    @FindBy(xpath = "//ul[@aria-labelledby=\"form_program-button\"]//child::li")
    public List<WebElement> mortgageType;

    @FindBy(xpath = "//ul[@id=\"form_category-menu\"]//child::li")
    public List<WebElement> client;

    @FindBy(xpath = "//ul[@id=\"form_documents-menu\"]//child::li")
    public List<WebElement> levelOfIncome;

    @FindBy(xpath = "//input[@id=\"form_credit_amount\"]")
    public WebElement getSum;

    @FindBy(xpath = "//input[@id=\"form_initial\"]")
    public WebElement installment;

    @FindBy(xpath = "//input[@id=\"form_period\"]")
    public WebElement term;

    @FindBy(xpath = "//input[@value=\"Рассчитать\"]")
    public WebElement resultButton;

    @FindBy(xpath = "//span[@class=\"monthly-payment\"]")
    public WebElement paymentForMonth;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right total-payment\"]")
    public WebElement totalPayment;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right percent-sum\"]")
    public WebElement percentSum;

    @FindBy(xpath = "//div[@class=\"b-calc-result__text--right interest-rate\"]")
    public WebElement rate;

    @Step("проверен заголовок страницы")
    public void checkHeader() {
        compareText(header, "Ипотечный калькулятор");
    }

    @Step("выбран город - {0}")
    public void selectCity(String city){
        scrollToElement(Init.getDriver().findElement(By.xpath("//select[@id=\"form_city\"]/parent::div")));
        Init.getDriver().findElement(By.xpath("//select[@id=\"form_city\"]/parent::div")).click();
        for(WebElement item : cityList){
            if(item.getText().equalsIgnoreCase(city)) {
                new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
        Assert.fail("Не найден такой город " + city);
    }

    @Step("отмечен чек-бокс ипотечной программы")
    public void checkBoxCheck() throws InterruptedException {
        checkBox(checkBox);
        Thread.sleep(2000);
    }

    @Step("выбран вид ипотечной программы - {0}")
    public void chooseProgram(String program){
        scrollToElement(Init.getDriver().findElement(By.xpath("//select[@id=\"form_program\"]/parent::div")));
        click(Init.getDriver().findElement(By.xpath("//select[@id=\"form_program\"]/parent::div")));
        for(WebElement item : mortgageType){
            if(item.getText().equalsIgnoreCase(program)) {
                new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
        Assert.fail("Не найден такой вид ипотечной программы " + program);
    }

    @Step("выбран статус клиента - {0}")
    public void chooseClientStatus(String status) throws InterruptedException {
        Thread.sleep(2000);
        scrollToElement(Init.getDriver().findElement(By.xpath("//div[contains(text(),'Я являюсь')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")));
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Я являюсь')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        for(WebElement item : client){
            if(item.getText().equalsIgnoreCase(status)) {
                new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
        Assert.fail("Не найден такой статус клиента " + status);
    }

    @Step("выбран уровень дохода - {0}")
    public void chooseIncomeLevel(String level) throws InterruptedException {
        Thread.sleep(2000);
        Init.getDriver().findElement(By.xpath("//div[contains(text(),'Уровень доходов')]//parent::div[@class=\"input-block input-block--triangle input-block--filled\"]")).click();
        for(WebElement item : levelOfIncome){
            if(item.getText().equalsIgnoreCase(level)) {
                new WebDriverWait(Init.getDriver(), 10).until(ExpectedConditions.elementToBeClickable(item)).click();
                return;
            }
        }
        Assert.fail("Не найден такой уровень дохода " + level);
    }

    @Step("выбрана сумма, которую берем у банка - {0}")
    public void chooseSum(String money) throws InterruptedException {
        Thread.sleep(3000);
        click(getSum);
        fillField(getSum,money);
    }

    @Step("выбранна сумма первоначального взноса - {0}")
    public void chooseFirstInstallment(String money) throws InterruptedException {
        Thread.sleep(2000);
        click(installment);
        fillField(installment,money);
    }

    @Step("выбран срок кредита - {0}")
    public void chooseCreditTerm(String creditTerm) throws InterruptedException {
        Thread.sleep(2000);
        click(term);
        fillField(term, creditTerm);
    }

    @Step("нажата кнопка \"Рассчитать\"")
    public void calculateButton() throws InterruptedException {
        Thread.sleep(2000);
        click(resultButton);
    }

    @Step("проверены итоговые данные, для сравнения с данными: ежемесячный платеж - {0}, общая сумма выплат - {1}, сумма выплат по процентам - {2}, процентная ставка - {3}")
    public void checkFinalResults(String resultPaymentForMonth, String resultTotalPayment, String resultPercentSum, String resultRate) {
        WebDriverWait wait = new WebDriverWait(Init.getDriver(), 30);
        wait.until((ExpectedCondition<Boolean>) driver -> {
                    if (paymentForMonth.getText().replace("руб.", "").replaceAll(" ", "").equals(resultPaymentForMonth)
                            && totalPayment.getText().replace("руб.", "").replaceAll(" ", "").equals(resultTotalPayment)
                            && percentSum.getText().replace("руб.", "").replaceAll(" ", "").equals(resultPercentSum)
                            && rate.getText().replace("%", "").replaceAll(" ", "").equals(resultRate)) {
                        return Boolean.TRUE;
                    }
                    return false;
                });
    }



}
