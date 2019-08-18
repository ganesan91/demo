package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiBaseClass {

    public static WebDriver driver;
    private final String GET_URL = "https://polar-peak-60366.herokuapp.com";

    public UiBaseClass() {
    }

    public void setupEnvironment()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        getPage();
    }

    public void getPage()
    {
        driver.get(GET_URL);
    }

    public WebDriver getDriver()
    {
        return driver;
    }

    public void closeBrowser() {
        if(driver!=null) {
            driver.close();
        }
    }

    public WebElement findElement(By by) {

        System.out.println("Using By {}" + by.toString());
        WebElement value = null;

        try {
            value = driver.findElement(by);
        } catch (NoSuchElementException e) {
            // the element can not be found
        }

        return value;
    }
}
