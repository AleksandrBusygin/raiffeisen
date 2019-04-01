package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.CalculatorPage;


public class CalculatorSteps {

    public CalculatorPage calculatorPage = new CalculatorPage();

    @When("проверен заголовок страницы")
    public void checkHeaderStep() {
        calculatorPage.checkHeader();
    }

    @When("выбран город \"(.+)\"")
    public void selectCityStep(String city) {
        calculatorPage.selectCity(city);
    }

    @When("выбрана ипотечная программа")
    public void clickCheckBox() {
        calculatorPage.checkBoxCheck();
    }

    @When("выбран вид ипотечной программы \"(.+)\"")
    public void selectProgramStep(String value) {
        calculatorPage.chooseProgram(value);
    }

    @When("выбран статус клиента \"(.+)\"")
    public void selectClientStatusStep(String value) {
        calculatorPage.chooseClientStatus(value);
    }

    @When("выбран уровень доходов \"(.+)\"")
    public void selectIncomeLevelStep(String value) {
        calculatorPage.chooseIncomeLevel(value);
    }

    @When("выбрана сумма, которую берем у банка \"(.+)\"")
    public void selectSumStep(String value) {
        calculatorPage.chooseSum(value);
    }

    @When("выбран первоначальный взнос \"(.+)\"")
    public void selectFirstInstallment(String value) {
        calculatorPage.chooseFirstInstallment(value);
    }

    @When("выбран срок кредита \"(.+)\"")
    public void selectCreditTermStep(String value) {
        calculatorPage.chooseCreditTerm(value);
    }

    @When("нажата кнопка рассчета")
    public void calculate() {
        calculatorPage.calculateButton();
    }

    @Then("проверить введенные значения - ежемесячный платеж \"(.+)\" , общая сумма выплат \"(.+)\" , сумма выплат по процентам \"(.+)\" , процентная ставка \"(.+)\"")
    public void checkFinalResultsStep(String monthPayment,String totalPayment, String percentSum, String rate) {
        calculatorPage.checkPaymentForMonth(monthPayment);
        calculatorPage.checkTotalPayment(totalPayment);
        calculatorPage.checkPercentSum(percentSum);
        calculatorPage.checkRate(rate);
    }

}
