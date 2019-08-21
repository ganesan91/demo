import DataProviders.DataProviderClass;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.QuestionsPage;
import utils.ui.UiHelperClass;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class UiTestClass{

    public UiHelperClass uiHelperClass;
    private LoginPage loginPage;
    private QuestionsPage questionsPage;

    private static String QUESTION_TEXT = "Sample";
    private static String UNAUTHORIZED = "unauthorized";
    private static String SELECTED = "selected";

    @Parameters({ "browser" })
    @BeforeClass
    public void setup(String browser) {
        uiHelperClass = new UiHelperClass();
        loginPage = new LoginPage();
        questionsPage = new QuestionsPage();
        loginPage.setupEnvironment(browser);
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "uiLoginTestDataProvider")
    public void loginTest(String username, String password, By by, boolean rememberMe) {
        loginPage.loginToPageWithGivenData(username, password, rememberMe);
        Assert.assertNotNull(by, "Expected Object should not be null.");
        Assert.assertNotNull(loginPage.isElementDisplayed(by), "Element Not Found - " + by.toString());
        System.out.println("Test case execution completed.");
    }

    @Test
    public void checkQuestionsTabWithoutLogin() {
        loginPage.goToQuestionTab();
        Assert.assertTrue(uiHelperClass.getCurrentUrl().contains(UNAUTHORIZED), "URL didn't redirect to Unauthorized page." + uiHelperClass.getCurrentUrl());
        Assert.assertNotNull(questionsPage.getUnauthorizedText(), "Unauthorized message is missing.");
    }

    @Test
    public void checkQuestionsTabAfterLogin() {
        loginPage.loginAndGoToQuestionTab();
        Assert.assertTrue(questionsPage.getClassValueForListMenu().contains(SELECTED), "List should be selected by default.");
        Assert.assertNotNull(questionsPage.isQuestionsListed(), "Questions are not listed in list meanu.");
    }

    @Test
    public void deleteQuestionTest(){
        loginPage.loginAndGoToQuestionTab();
        questionsPage.deleteQuestion(QUESTION_TEXT);
        loginPage.refreshPage();
        Assert.assertNull(questionsPage.getDeleteButtonForQuestion(QUESTION_TEXT), "Question is not deleted");
    }

    @Test(dataProviderClass = DataProviderClass.class, dataProvider = "uiCreateQuestionTestDataProvider")
    public void createQuestionTest(String question, String type, List<String> options, boolean expectedStatus, By expectedResult, boolean validateExpected) {
        loginPage.loginAndGoToQuestionTab();
        questionsPage.goToCreateQuestionTab();
        questionsPage.createQuestion(question, type, options, expectedResult, validateExpected);
        questionsPage.goToListQuestionTab();
        Assert.assertEquals(questionsPage.deleteQuestion(question) == null, expectedStatus, "Validation failed.");
    }

    @Test
    public void CheckMenuTabSelectedOnRefresh() {
        loginPage.loginToPage();
        Assert.assertTrue(loginPage.isHomeTabSelected(), "Home tab should be selected by default.");
        loginPage.goToQuestionTab();
        loginPage.refreshPage();
        Assert.assertTrue(loginPage.isQuestionTabSelected(), "Question tab should be selected after page refresh.");
    }

    @Test
    public void CheckQuestionMenuTabSelectedOnRefresh() {
        loginPage.loginAndGoToQuestionTab();
        Assert.assertTrue(questionsPage.isQuestionListTabSelected(), "List tab should be selected by default.");
        questionsPage.goToCreateQuestionTab();
        loginPage.refreshPage();
        Assert.assertTrue(questionsPage.isQuestionCreateTabSelected(), "Question create tab should be selected after page refresh.");
    }

    @Test
    public void checkQuestionsTabAfterLogOut() {
        loginPage.loginToPage();
        loginPage.logout();
        loginPage.goToQuestionTab();
        Assert.assertTrue(uiHelperClass.getCurrentUrl().contains(UNAUTHORIZED), "URL didn't redirect to Unauthorized page." + uiHelperClass.getCurrentUrl());
        Assert.assertNotNull(questionsPage.getUnauthorizedText(), "Unauthorized message is missing.");
    }

    @AfterMethod
    public void refreshPageAndContinueTest() {
        loginPage.returnToHomePage();
        loginPage.logoutIfLoggedIn();
        loginPage.refreshPage();
    }

    @AfterClass
    public void closeBrowser() {
        loginPage.closeBrowser();
    }
}
