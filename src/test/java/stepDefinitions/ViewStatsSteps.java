package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.DashboardPage;
import page.LandingPage;
import page.LoginPage;
import utils.WebDriverManager;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ViewStatsSteps {
    private WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage login;
    LandingPage landingPage;

    void setup() {
        driver = WebDriverManager.getDriver();
        login = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        landingPage = new LandingPage(driver);
    }


    @When("I click on the report menu item")
    public void iClickOnTheMenu() {
        setup();
        dashboardPage.clickNavBtn();
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

    }

    @When("I explore the statistics details by filter")
    public void iExploreTheStatisticsDetailsByFilter() {
        dashboardPage.clickFilter();
    }

    @Then("I should be able to see another data")
    public void iShouldBeAbleToSeeAnotherData() {
        dashboardPage.checkCharts();
        dashboardPage.checkAllCharts();
    }

    @After
    public void closeBrowser() {
        WebDriverManager.quitDriver();
    }
}

