package page;

import object.DashboardObject;
import object.LoginObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DashboardPage {

    WebDriver driver;
    DashboardObject dashboardObject;
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        dashboardObject = new DashboardObject(driver);
    }

    public void checkAvatar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardObject.getAvatarMenu()));
        assertNotNull(avatar);
    }

    public void clickAvatar(){
        driver.findElement(dashboardObject.getAvatarMenu()).click();
    }

    public void getTooltip(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardObject.getToolTip()));
        assertNotNull(tooltip);
    }

    public void clickLogout(){
        driver.findElement(dashboardObject.getLogoutButton()).click();
    }


    public String getURL() {
        return driver.getCurrentUrl();
    }

}
