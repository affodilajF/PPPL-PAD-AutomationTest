package stepDefinitions;

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
    LoginPage login;
    DashboardPage dashboardPage;

    void setupDriver() {
        driver = WebDriverManager.getDriver();
    }

    @Given("User in the admin login page")
    public void user_in_the_admin_login_page() {
        setupDriver();
        String url = "http://localhost:5173/login";
        driver.get(url);
    }
    @When("user submit valid credentials")
    public void user_submit_valid_credentials() {
        login = new LoginPage(driver);
        login.clickSubmit();

    }

    @Then("the user is redirected to the dashboard page")
    public void the_user_is_redirected_to_the_dashboard_page() {
        String expectedURL = "http://localhost:5173/manage";
        DashboardPage dashboardPage = new DashboardPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);
    }

    @After
    public void closeBrowser() {
            WebDriverManager.quitDriver();
    }
}
