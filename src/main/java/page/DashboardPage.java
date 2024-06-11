package page;

import object.DashboardObject;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;
    DashboardObject dashboardObject;
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
//        dashboardObject = new DashboardObject(driver);
    }
//    public void isCongratulationsTxtDisplayed() {
//        driver.findElement(dashboardObject.getTxtCongratulations()).isDisplayed();
//    }
//    public void isLogoutButtonDisplayed() {
//        driver.findElement(dashboardObject.getBtnLogout()).isDisplayed();
//    }
    public String getURL() {
        return driver.getCurrentUrl();
    }

}
