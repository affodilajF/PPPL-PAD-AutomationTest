package object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardObject {

    WebDriver driver;

    public DashboardObject(WebDriver driver) {
        this.driver = driver;
    }

    public By getTxtCongratulations() {
        return By.xpath("//*[contains(text(), 'Congratulations')]");
    }

    public By getBtnLogout(){
        return By.xpath("//*[contains(text(), 'Log out')]");
    }

}

