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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.DashboardPage;
import page.LandingPage;
import page.LoginPage;
import utils.ExtentReportManager;
import utils.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutSteps {
    private WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage login;
    LandingPage landingPage;
    ExtentReports extent;
    ExtentTest test;

    void setup() {
        driver = WebDriverManager.getDriver();
        login = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        landingPage = new LandingPage(driver);
    }

    @Before
    public void init() {
        extent = ExtentReportManager.getInstance();
        test = extent.createTest("Logout Test", "Test the logout functionality");
    }

    @Given("I am logged in as an admin")
    public void loggedInAsAdmin(){
        setup();
        String url = "http://localhost:5173/login";
        driver.get(url);
        login.clickSubmit();
        test.log(Status.PASS, "Logged in as admin");
    }

    @Given("I am on the dashboard page")
    public void onDashboardPage() {
        String expectedURL = "http://localhost:5173/manage";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);
        test.log(Status.PASS, "User is on the dashboard page");
    }

    @When("I click on my profile avatar")
    public void user_click_profile_avatar() {
        dashboardPage.checkAvatar();
        dashboardPage.clickAvatar();
        test.log(Status.PASS, "Clicked on profile avatar");
    }

    @Then("a tooltip should be displayed")
    public void the_tooltip_is_shown() {
        dashboardPage.getTooltip();
        test.log(Status.PASS, "Tooltip is displayed");
    }

    @When("I click on the logout button")
    public void user_click_logout_button() {
        dashboardPage.clickLogout();
        test.log(Status.PASS, "Clicked on logout button");
    }

    @Then("I should be redirected to the landing page")
    public void the_user_is_redirected_to_the_landing_page() {
        String expectedURL = "http://localhost:5173/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = landingPage.getURL();
        assertEquals(expectedURL, actualURL);
        test.log(Status.PASS, "User is redirected to the landing page");
    }

    @After
    public void closeBrowser() {
        if (driver != null) {
            WebDriverManager.quitDriver();
            test.log(Status.PASS, "Browser closed");
        }
        extent.flush();
    }
}
