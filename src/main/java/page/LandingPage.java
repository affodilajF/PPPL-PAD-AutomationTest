package page;

import object.DashboardObject;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }
}
