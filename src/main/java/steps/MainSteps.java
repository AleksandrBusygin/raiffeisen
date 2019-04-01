package steps;

import cucumber.api.java.en.When;
import pages.MainPage;

public class MainSteps {

    MainPage mainPage = new MainPage();

    @When("подтвержден город")
    public void pressButton(){
        mainPage.selectButton();
    }

    @When("загружена главная страница, выбираем раздел \"(.+)\"")
    public void loadPage(String value) {
        mainPage.selectMainMenu(value);
    }

    @When("выбран ипотечный калькулятор для оформления ипотеки")
    public void selectCalcStep(){
        mainPage.chooseCalc();
    }

}
