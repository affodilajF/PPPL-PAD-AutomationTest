package page;

import object.DashboardObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    By chart = By.tagName("canvas");
    By filterBtn = By.xpath("//button[text()='Q1']");


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

    public void clickNavBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Tunggu hingga elemen ada di DOM
        WebElement report_btn = wait.until(ExpectedConditions.presenceOfElementLocated(reportNavBtn));

        // Coba scroll ke elemen menggunakan JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", report_btn);

        // Tunggu sebentar setelah scroll
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Tunggu hingga elemen dapat diklik
        wait.until(ExpectedConditions.elementToBeClickable(reportNavBtn));

        // Coba klik menggunakan JavaScript
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", report_btn);
        } catch (Exception e) {
            // Jika JavaScript click gagal, coba menggunakan Actions
            new Actions(driver).moveToElement(report_btn).click().perform();
        }

        // Verifikasi bahwa klik berhasil (misalnya dengan memeriksa URL baru)
        wait.until(ExpectedConditions.urlContains("/manage/reports"));
    }

    public void checkCharts(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement charts = wait.until(ExpectedConditions.visibilityOfElementLocated(chart));
        assertNotNull(charts);
    }

    public void checkAllCharts(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Tunggu sampai setidaknya satu grafik muncul
        wait.until(ExpectedConditions.presenceOfElementLocated(chart));

        // Tunggu sampai semua grafik terlihat
        wait.until(driver -> {
            List<WebElement> charts = driver.findElements(chart);
            return charts.size() == 6 && charts.stream().allMatch(WebElement::isDisplayed);
        });

        List<WebElement> charts = driver.findElements(chart);
        int count = charts.size();
        int expectedCount = 6;
        Assert.assertEquals("Jumlah grafik tidak sesuai yang diharapkan", expectedCount, count);

        // Verifikasi bahwa semua grafik terlihat
        for (WebElement chartElement : charts) {
            assertTrue("Grafik tidak terlihat", chartElement.isDisplayed());
        }
    }

    public void clickFilter(){
        driver.findElement(filterBtn).click();
    }


    public String getURL() {
        return driver.getCurrentUrl();
    }

}
