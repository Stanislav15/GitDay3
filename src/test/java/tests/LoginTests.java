package tests;

import com.oracle.tools.packager.mac.MacAppBundler;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
//Login tests are maintained here
//without Page object approach
public class LoginTests {
    WebDriver driver;

    Map<String,String> myMap = new HashMap<>();

    @BeforeMethod
    public void setUo(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    @Test
    public void loginTests(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
        String title = driver.getTitle();

        Assert.assertEquals(title,"Web Orders");
    }


    @Test
    public void LogOutTest(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test"+ Keys.ENTER);
        String title = driver.getTitle();
        driver.findElement(By.id("ctl00_logout")).click();
        Assert.assertEquals(title,"Web Orders Login");


    }

    @Test
    public void negativeloginTests(){
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester2");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test2"+ Keys.ENTER);
        String erroMsg = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(erroMsg,"Ivalid Login or Password.");
    }

    @AfterMethod
    public void cleanUp(){
        driver.close();
    }

}
