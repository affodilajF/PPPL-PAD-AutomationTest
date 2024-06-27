package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.DashboardPage;
import page.LandingPage;
import page.LoginPage;
import utils.WebDriverManager;

public class CrudSectorSteps {
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

    @When("I click on the table menu in debtor distribution")
    public void iClickOnTheTableMenu() {
        setup();
        dashboardPage.clickTableSection();
    }

    @Then("I should see the table")
    public void iSeeTheTable() {
        dashboardPage.checkSectorTable();
    }

    @When("I click Tambah Data Penyaluran button")
    public void iClickOnTheAddDataTable() {
        dashboardPage.clickAddTableData();
    }

    @Then("I should see the form")
    public void iSeeTheForm() {
        dashboardPage.checkFormPresence();
    }

    @When("I fill out the correct data")
    public void iFillOutData() {
        dashboardPage.inputFormSector();
        dashboardPage.submitForm();
    }

    @Then("I should see successful toast")
    public void iSeeSuccessToast() {
        dashboardPage.checkAlert();
    }


}
