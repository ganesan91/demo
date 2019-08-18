import Utils.LoginPage;
import Utils.UiBaseClass;
import Utils.UiHelperClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UiTestClass extends UiBaseClass{

    public UiHelperClass uiHelperClass;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        uiHelperClass = new UiHelperClass();
        loginPage = new LoginPage();
        setupEnvironment();
    }

    @Test
    public void loginTest() {
        try {
            loginPage.getLoginPageWithData();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(loginPage.isLogoutElementVisible());
    }

    @AfterClass
    public void closeBrowser() {
        loginPage.closeBrowser();
    }
}
