package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.DashboardPage;
import page.LoginPage;
import utils.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    private WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    void setupDriver() {
        //driver = Hooks.getDriver();
        driver = WebDriverManager.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        System.out.println(Hooks.getDriver());
    }

    @Given("User in the admin login page")
    public void user_in_the_admin_login_page() {
        setupDriver();
        String url = "http://localhost:5173/login";
        driver.get(url);
    }
    @When("user submit valid credentials")
    public void user_submit_valid_credentials() {
        loginPage.clickSubmit();
    }

    @Then("the user is redirected to the dashboard page")
    public void the_user_is_redirected_to_the_dashboard_page() {
        String expectedURL = "http://localhost:5173/manage";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);
    }
//
//    @After
//    public void closeBrowser() {
//            driver.quit();
//    }
}