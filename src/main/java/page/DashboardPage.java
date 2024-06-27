package page;

import object.DashboardObject;
import org.junit.Assert;
import object.LoginObject;
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
        dashboardObject = new DashboardObject(driver);
    }

    By reportNavBtn = By.xpath("//a[@href='/manage/reports']");
    By chart = By.tagName("canvas");
    By filterBtn = By.xpath("//button[text()='Q1']");
    By tableSectorBtn = By.xpath("/html/body/div/div[1]/div/main/div/div/div[6]/div[1]/div/button[2]");
    By sectorTable = By.xpath("/html/body/div/div[1]/div/main/div/div/div[6]/div[2]/div[2]/div/table");
    By addDataTable = By.xpath("/html/body/div/div[1]/div/main/div/div/div[6]/div[2]/div[1]/div[2]/button");
    By formSectorTable = By.xpath("/html/body/div/div[1]/div/main/div/div[3]/div/div/div[2]");
    By inputBusinessType = By.xpath("//input[@id='business_type_id']");
    By inputYear = By.xpath("//input[@id='year']");
    By inputMonth = By.xpath("//input[@id='month']");
    By inputDebtor = By.xpath("//input[@id='debtor']");
    By inputContractValue = By.xpath("//input[@id='contract_value']");
    By inputOutstandingValue = By.xpath("//input[@id='outstanding_value']");
    By inputTarget = By.xpath("//input[@id='target']");
    By inputRealization = By.xpath("//input[@id='realization']");
    By submitBtn = By.xpath("/html/body/div/div[1]/div/main/div/div[3]/div/div/div[2]/form/div/div[9]/button[1]");
    By alert = By.xpath("//div[@role='alert']");




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

    public void clickNavBtn(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        WebElement report_btn = wait.until(ExpectedConditions.presenceOfElementLocated(reportNavBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", report_btn);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(reportNavBtn));

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", report_btn);
        } catch (Exception e) {
            new Actions(driver).moveToElement(report_btn).click().perform();
        }
        wait.until(ExpectedConditions.urlContains("/manage/reports"));
    }

    public void checkCharts(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement charts = wait.until(ExpectedConditions.visibilityOfElementLocated(chart));
        assertNotNull(charts);
    }

    public void checkAllCharts(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));


        wait.until(ExpectedConditions.presenceOfElementLocated(chart));

        wait.until(driver -> {
            List<WebElement> charts = driver.findElements(chart);
            return charts.size() == 6 && charts.stream().allMatch(WebElement::isDisplayed);
        });

        List<WebElement> charts = driver.findElements(chart);
        int count = charts.size();
        int expectedCount = 6;
        Assert.assertEquals("Jumlah grafik tidak sesuai yang diharapkan", expectedCount, count);


        for (WebElement chartElement : charts) {
            assertTrue("Grafik tidak terlihat", chartElement.isDisplayed());
        }
    }

    public void clickFilter(){
        driver.findElement(filterBtn).click();
    }

    public void clickTableSection(){
        driver.findElement(tableSectorBtn).click();
    }

    public void checkSectorTable(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        wait.until(ExpectedConditions.presenceOfElementLocated(sectorTable));
        WebElement table = driver.findElement(sectorTable);
        assertNotNull(table);
    }

    public void clickAddTableData(){
        driver.findElement(addDataTable).click();
    }

    public void checkFormPresence(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(formSectorTable));
        WebElement form = driver.findElement(formSectorTable);
        assertNotNull(form);
    }

    public void inputFormSector(){
        driver.findElement(inputBusinessType).sendKeys("ALG86qepwdlBr3X");
        driver.findElement(inputYear).sendKeys("2024");
        driver.findElement(inputMonth).sendKeys("06");
        driver.findElement(inputDebtor).clear();
        driver.findElement(inputDebtor).sendKeys("12");
        driver.findElement(inputContractValue).clear();
        driver.findElement(inputContractValue).sendKeys("12");
        driver.findElement(inputOutstandingValue).clear();
        driver.findElement(inputOutstandingValue).sendKeys("12");
        driver.findElement(inputTarget).clear();
        driver.findElement(inputTarget).sendKeys("12");
        driver.findElement(inputRealization).clear();
        driver.findElement(inputRealization).sendKeys("12");
    }

    public void submitForm(){
        driver.findElement(submitBtn).click();
    }

    public void checkAlert(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        wait.until(ExpectedConditions.presenceOfElementLocated(alert));
        WebElement alertElement = driver.findElement(alert);
        assertNotNull(alertElement);
    }

    public String getURL() {
        return driver.getCurrentUrl();
    }

}
