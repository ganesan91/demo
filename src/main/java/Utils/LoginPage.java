package Utils;

import org.openqa.selenium.By;

public class LoginPage extends UiBaseClass {

    private static String USERNAME = "test";
    private static String PASSWORD = "test";
    private final By USERNAME_TEXT = By.xpath("//*[@id=\"normal_login_username\"]");
    private final By PASSWORD_TEXT = By.xpath("//*[@id=\"normal_login_password\"]");
    private final By SUBMIT_USERNAME_PASSWORD = By.xpath("//*[@type=\"submit\"]");
    private final By LOGOUT_BUTTON = By.className("logout-form-button");


    public LoginPage() {

    }

    public void getLoginPageWithData()
    {
        findElement(USERNAME_TEXT).click();
        findElement(USERNAME_TEXT).clear();
        findElement(USERNAME_TEXT).sendKeys(USERNAME);
        findElement(PASSWORD_TEXT).click();
        findElement(PASSWORD_TEXT).clear();
        findElement(PASSWORD_TEXT).sendKeys(PASSWORD);
        findElement(SUBMIT_USERNAME_PASSWORD).click();
    }

    public String getCurrentUrl()
    {
        return getDriver().getCurrentUrl();
    }

    public String isLogoutElementVisible()
    {
        return findElement(LOGOUT_BUTTON).getText();
    }

    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }
}
