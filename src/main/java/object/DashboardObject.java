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

    public By getLogoutButton(){
        return By.xpath("//button[text()='Logout']");
    }

    public By getAvatarMenu(){
        return By.xpath("//img[@id='avatar-menu']");
    }

    public By getToolTip(){
        return By.xpath("//div[@role='tooltip']");
    }
}

