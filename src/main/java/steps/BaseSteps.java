package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.Init;
import util.TestProperties;


import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {

    private static Properties properties = TestProperties.getInstance().getProperties();

    @Before("@web")
    public static void setUp() {
        switch (properties.getProperty("browser2")){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.gecko.driver"));
                Init.setDriver(new FirefoxDriver());
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                Init.setDriver(new ChromeDriver());
                break;
            default:
                System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome.driver"));
                Init.setDriver(new ChromeDriver());
        }
        String baseUrl = properties.getProperty("app.url");
        Init.getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        Init.getDriver().manage().window().maximize();
        Init.getDriver().get(baseUrl);
    }

    @After("@web")
    public static void tearDown() {
        Init.getDriver().quit();
    }
}
