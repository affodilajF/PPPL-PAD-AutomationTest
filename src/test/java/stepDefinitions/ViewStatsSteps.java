package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
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

public class ViewStatsSteps {
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
        test = extent.createTest("View Stats Test", "Test the view stats functionality");
    }

    @When("I click on the report menu item")
    public void iClickOnTheMenu() {
        setup();
        dashboardPage.clickNavBtn();
        test.log(Status.PASS, "Clicked on the report menu item");
    }

    @Then("I should see the overview of platform statistics")
    public void iShouldSeeTheOverviewOfPlatformStatistics() {
        String expectedURL = "http://localhost:5173/manage/reports";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);

        dashboardPage.checkCharts();
        dashboardPage.checkAllCharts();
        test.log(Status.PASS, "Overview of platform statistics is displayed");
    }

    @When("I explore the statistics details by filter")
    public void iExploreTheStatisticsDetailsByFilter() {
        dashboardPage.clickFilter();
        test.log(Status.PASS, "Explored the statistics details by filter");
    }

    @Then("I should be able to see another data")
    public void iShouldBeAbleToSeeAnotherData() {
        dashboardPage.checkCharts();
        dashboardPage.checkAllCharts();
        test.log(Status.PASS, "Another data is displayed");
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
