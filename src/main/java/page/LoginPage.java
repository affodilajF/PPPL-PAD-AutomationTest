package page;

import object.LoginObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    WebDriver driver;
    LoginObject loginObject;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
//        loginObject = new LoginObject(driver);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }


    public String getURL() {
        return driver.getCurrentUrl();
    }
//

}
