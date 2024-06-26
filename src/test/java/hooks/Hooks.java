package hooks;

//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Before;
//import io.cucumber.java.en.After;
//import org.junit.After;
//import org.junit.Before;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Hooks {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            System.out.println(" yea " + driver);
        }
    }

    @AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setUp();
        }
        return driver;
    }
}
