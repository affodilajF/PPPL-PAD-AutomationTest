package stepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.DashboardPage;
import page.LandingPage;
import page.LoginPage;
import utils.ExtentReportManager;
import utils.WebDriverManager;

public class CrudSectorSteps {
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
        test = extent.createTest("CRUD Sector Test", "Test the CRUD operations for the sector");
    }

    @When("I click on the table menu in debtor distribution")
    public void iClickOnTheTableMenu() {
        setup();
        dashboardPage.clickTableSection();
        test.log(Status.PASS, "Clicked on the table menu in debtor distribution");
    }

    @Then("I should see the table")
    public void iSeeTheTable() {
        dashboardPage.checkSectorTable();
        test.log(Status.PASS, "Sector table is displayed");
    }

    @When("I click Tambah Data Penyaluran button")
    public void iClickOnTheAddDataTable() {
        dashboardPage.clickAddTableData();
        test.log(Status.PASS, "Clicked on the 'Tambah Data Penyaluran' button");
    }

    @Then("I should see the form")
    public void iSeeTheForm() {
        dashboardPage.checkFormPresence();
        test.log(Status.PASS, "Form is displayed");
    }

    @When("I fill out the correct data")
    public void iFillOutData() {
        dashboardPage.inputFormSector();
        dashboardPage.submitForm();
        test.log(Status.PASS, "Filled out the form with correct data and submitted");
    }

    @Then("I should see successful toast")
    public void iSeeSuccessToast() {
        dashboardPage.checkAlert();
        test.log(Status.PASS, "Success toast is displayed");
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
