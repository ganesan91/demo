package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ui.UiBaseClass;

public class LoginPage extends UiBaseClass {

    private static String USERNAME = "test";
    private static String PASSWORD = "test";
    private final By USERNAME_TEXT = By.xpath("//*[@id=\"normal_login_username\"]");
    private final By PASSWORD_TEXT = By.xpath("//*[@id=\"normal_login_password\"]");
    private final By SUBMIT_USERNAME_PASSWORD = By.xpath("//*[@type=\"submit\"]");
    private final By LOGOUT_BUTTON = By.className("logout-form-button");
    private final By USERNAME_ERROR_TEXT = By.xpath("//*[text()='Please input your username!']");
    private final By PASSWORD_ERROR_TEXT = By.xpath("//*[text()='Please input your Password!']");
    private final By LOGIN_ERROR_TEXT = By.xpath("//*[text()='Login Failed!']");
    private final By REMEMBER_ME = By.xpath("//*[@type=\"checkbox\"]");
    private final By QUESTIONS_TAB = By.xpath("//*[@id=\"root\"]/section/header/ul/li[4]/a");
    private final By HOME_TAB = By.xpath("//*[@id=\"root\"]/section/header/ul/li[2]/a");

    public LoginPage() {

    }

    public void loginToPageWithGivenData(String username, String password, boolean rememberMe)
    {
        WebElement input_username = findElement(USERNAME_TEXT);
        WebElement input_password = findElement(PASSWORD_TEXT);
        input_username.click();
        input_username.clear();
        input_username.sendKeys(username);
        input_password.click();
        input_password.clear();
        input_password.sendKeys(password);

        if(rememberMe)
        {
            enableRememeberMe();
        }
        else
        {
            disableRememeberMe();
        }

        findElement(SUBMIT_USERNAME_PASSWORD).click();
    }

    public void loginToPage()
    {
        loginToPageWithGivenData(USERNAME, PASSWORD, true);
        isElementDisplayed(LOGOUT_BUTTON);
    }

    public void logoutIfLoggedIn() {
        if(isElementVisible(LOGOUT_BUTTON))
        {
            findElement(LOGOUT_BUTTON).click();
        }
    }

    public WebElement isElementDisplayed(By by)
    {
        return isElementDisplayed(by, getDriver());
    }

    public By getLogOutText()
    {
        return LOGOUT_BUTTON;
    }

    public By getUsernameErrorText()
    {
        return USERNAME_ERROR_TEXT;
    }

    public By getPasswordErrorText()
    {
        return PASSWORD_ERROR_TEXT;
    }

    public By getLoginErrorText()
    {
        return LOGIN_ERROR_TEXT;
    }

    public void refreshPage()
    {
        refresh(getDriver());
    }

    public void enableRememeberMe() {
        if(!isChecked(REMEMBER_ME))
        {
            clickCheckBox(REMEMBER_ME);
        }
    }

    public void disableRememeberMe() {
        if(isChecked(REMEMBER_ME))
        {
            clickCheckBox(REMEMBER_ME);
        }
    }

    public void loginAndGoToQuestionTab() {
        loginToPage();
        goToQuestionTab();
    }

    public void goToQuestionTab() {
        goTo(QUESTIONS_TAB);
    }

    public void returnToHomePage() {
        goTo(HOME_TAB);
    }

    public boolean isQuestionTabSelected() {
        return isSelected(QUESTIONS_TAB);
    }

    public boolean isHomeTabSelected() {
        return isSelected(HOME_TAB);
    }

    public void logout() {
        returnToHomePage();
        findElement(LOGOUT_BUTTON).click();
    }

    @Override
    public void closeBrowser() {
        super.closeBrowser();
    }
}
