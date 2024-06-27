package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.DashboardPage;
import page.LoginPage;
import utils.ExtentReportManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {
    WebDriver driver;
    LoginPage login;
    DashboardPage dashboardPage;
    ExtentReports extent;
    ExtentTest test;

    void setupDriver() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Before
    public void setup() {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Login Test", "Test the login functionality");
    }

    @Given("User in the admin login page")
    public void user_in_the_admin_login_page() {
        setupDriver();
        String url = "http://localhost:5173/login";
        driver.get(url);
        test.log(Status.PASS, "User navigated to login page");
    }

    @When("user submit valid credentials")
    public void user_submit_valid_credentials() {
        login = new LoginPage(driver);
        login.clickSubmit();
        test.log(Status.PASS, "User submitted valid credentials");
    }

    @Then("the user is redirected to the dashboard page")
    public void the_user_is_redirected_to_the_dashboard_page() {
        String expectedURL = "http://localhost:5173/manage";
        DashboardPage dashboardPage = new DashboardPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);
        test.log(Status.PASS, "User was redirected to the dashboard page");
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            test.log(Status.PASS, "Browser closed");
        }
        extent.flush();
    }
}
