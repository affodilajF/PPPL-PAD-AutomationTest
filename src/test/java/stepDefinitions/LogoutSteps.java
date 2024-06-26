package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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

public class LogoutSteps {
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

    @Given("I am logged in as an admin")
    public void loggedInAsAdmin(){
        setup();
        String url = "http://localhost:5173/login";
        driver.get(url);
        login.clickSubmit();
    }

    @Given("I am on the dashboard page")
    public void onDashboardPage() {
        String expectedURL = "http://localhost:5173/manage";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = dashboardPage.getURL();
        assertEquals(expectedURL, actualURL);
    }

    @When("I click on my profile avatar")
    public void user_click_profile_avatar() {
        dashboardPage.checkAvatar();
        dashboardPage.clickAvatar();
    }

    @Then("a tooltip should be displayed")
    public void the_tooltip_is_shown() {
        dashboardPage.getTooltip();
    }

    @When("I click on the logout button")
    public void user_click_logout_button() {
        // Write code here that turns the phrase above into concrete actions
        dashboardPage.clickLogout();
    }

    @Then("I should be redirected to the landing page")
    public void the_user_is_redirected_to_the_landing_page() {
        String expectedURL = "http://localhost:5173/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = landingPage.getURL();
        assertEquals(expectedURL, actualURL);
    }

}
