package utils.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UiBaseClass {

    public static WebDriver driver;
    private final String GET_URL = "https://polar-peak-60366.herokuapp.com";

    public UiBaseClass() {
    }

    public void setupEnvironment(String browser)
    {
        WebDriverManager.chromedriver().setup();
        if(browser.equalsIgnoreCase("firefox")){
            //create firefox instance
            // Will not work if geckodriver driver is not available
            System.setProperty("webdriver.firefox.marionette", ".\\geckodriver-driver-path");
            driver = new FirefoxDriver();
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //create chrome instance
            driver = new ChromeDriver();
        }
        else
        {
            System.out.println("Browser option given is not available");
        }
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

    public WebElement isElementDisplayed(By elementPath, WebDriver driver) {
        WebElement element = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(elementPath));
        return element;
    }

    public boolean isElementVisible(By by) {
        if(driver.findElements(by).size() != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isChecked(By by) {
        return findElement(by).isSelected();
    }

    public void clickCheckBox(By by) {
        findElement(by).click();
    }

    public void refresh(WebDriver driver) {
        driver.navigate().refresh();
    }

    public void goTo(By by) {
        findElement(by).click();
    }

    public String getClassValueOfParent(By by) {
        return findElement(by).findElement(By.xpath("./..")).getAttribute("class");
    }

    public String getClassValue(By by) {
        return findElement(by).getAttribute("class");
    }

    public boolean isSelected(By by) {
        return getClassValueOfParent(by).contains("selected");
    }
}
