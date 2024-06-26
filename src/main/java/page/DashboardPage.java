package page;

import object.DashboardObject;
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
    }

    By avatarMenu = By.xpath("//img[@id='avatar-menu']");
    By toolTip = By.xpath("//div[@role='tooltip']");
    By logoutBtn = By.xpath("//button[text()='Logout']");
    By reportNavBtn = By.xpath("//a[@href='/manage/reports']");


    public void checkAvatar() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated(avatarMenu));
        assertNotNull(avatar);
    }

    public void clickAvatar(){
        driver.findElement(avatarMenu).click();
    }

    public void getTooltip(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(toolTip));
        assertNotNull(tooltip);
    }

    public void clickLogout(){
        driver.findElement(logoutBtn).click();
    }




    public String getURL() {
        return driver.getCurrentUrl();
    }

}
