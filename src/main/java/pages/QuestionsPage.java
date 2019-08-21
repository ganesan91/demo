package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ui.UiBaseClass;

import java.util.ArrayList;
import java.util.List;

public class QuestionsPage extends UiBaseClass {

    private static String DYNAMIC_OPTION_INPUT = "dynamic_form_item_names";
    private final By UNAUTHORIZED_TEXT = By.xpath("//*[@id=\"root\"]/section/main/section/main/h1");
    private final By QUESTION_LIST_MENU = By.xpath("//*[@id=\"root\"]/section/main/section/main/section/aside/div/ul/li[1]/a");
    private final By QUESTION_CREATE_MENU = By.xpath("//*[@id=\"root\"]/section/main/section/main/section/aside/div/ul/li[2]/a");
    private final By GET_QUESTIONS_TABLE_BODY = By.xpath("//tbody[contains(@class, 'ant-table-tbody')]");
    private final By GET_DELETE_BUTTON = By.className("ant-btn");
    private final By CLICK_OK_ON_ALERT = By.className("ant-btn-primary");
    private final By CREATE_QUESTION_OPTIONS = By.className("ant-form-horizontal");
    private final By ENTER_QUESTION_INPUT = By.className("ant-input");
    private final By DROPDOWN_QUESTION_TYPE = By.className("ant-select-arrow");
    private final By ADD_QUESTION_OPTIONS_BUTTON = By.className("ant-btn-dashed");
    private final By CREATE_QUESTION_BUTTON = By.className("ant-btn-primary");
    private final By CREATE_QUESTION_SUCCESS_TEXT = By.xpath("//*[text()='Question created successfully']");
    private final By CREATE_QUESTION_ERROR_TEXT = By.xpath("//*[text()='Question creation failed']");
    private final By NEXT_BUTTON = By.className("ant-pagination-next");

    public QuestionsPage() {
    }

    public WebElement getUnauthorizedText() {
        return findElement(UNAUTHORIZED_TEXT);
    }

    public String getClassValueForListMenu() {
        return getClassValueOfParent(QUESTION_LIST_MENU);
    }

    public void goToCreateQuestionTab() {
        findElement(QUESTION_CREATE_MENU).click();
        isElementDisplayed(CREATE_QUESTION_OPTIONS, getDriver());
    }

    public void goToListQuestionTab() {
        findElement(QUESTION_LIST_MENU).click();
        isElementDisplayed(GET_QUESTIONS_TABLE_BODY, getDriver());
    }

    public String getClassValueForCreateMenu() {
        return getClassValueOfParent(QUESTION_CREATE_MENU);
    }

    public WebElement isQuestionsListed() {
        return isElementDisplayed(GET_QUESTIONS_TABLE_BODY, getDriver());
    }

    public WebElement deleteQuestion(String question){
        WebElement deleteElement = getDeleteButtonForQuestion(question);
        WebElement next_button = findElement(NEXT_BUTTON);
        while(deleteElement==null && !getClassValue(NEXT_BUTTON).contains("disabled"))
        {
            next_button.click();
            deleteElement = getDeleteButtonForQuestion(question);
        }

        if(deleteElement!=null)
        {
            deleteElement.findElement(GET_DELETE_BUTTON).click();
            findElement(CLICK_OK_ON_ALERT).click();
        }
        return deleteElement;
    }

    public void createQuestion(String question, String type, List<String> options, By expectedResult, boolean validateExpected) {
        int i=0;
        WebElement question_input = findElement(ENTER_QUESTION_INPUT);
        WebElement add_options_button = findElement(ADD_QUESTION_OPTIONS_BUTTON);
        question_input.click();
        question_input.clear();
        question_input.sendKeys( question);

        if(!isElementVisible(By.xpath("//*[text()='" + type + "']")))
        {
            findElement(DROPDOWN_QUESTION_TYPE).click();
            findElement(By.xpath("//*[text()='" + type + "']")).click();
        }

        for(String option: options)
        {
            add_options_button.click();
            findElement(By.id(DYNAMIC_OPTION_INPUT+"[" + i + "]")).click();
            findElement(By.id(DYNAMIC_OPTION_INPUT+"[" + i + "]")).sendKeys(option);
            i++;
        }
        findElement(CREATE_QUESTION_BUTTON).click();

        if(validateExpected)
        {
            isElementDisplayed(expectedResult, getDriver());
        }
    }

    public WebElement getDeleteButtonForQuestion(String question) {
        boolean flag = false;
        WebElement table = isQuestionsListed();
        List<WebElement> allRows = table.findElements(By.tagName("tr"));

        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.xpath("./*"));
            for (WebElement cell : cells) {
                if(cell.getText().equals(question))
                {
                    flag = true;
                }
                if(flag && cell.getText().equals("Delete"))
                {
                    return cell;
                }
            }
        }
        return null;
    }

    public By getCreateSuccessText()
    {
        return CREATE_QUESTION_SUCCESS_TEXT;
    }

    public By getCreateErrorText()
    {
        return CREATE_QUESTION_ERROR_TEXT;
    }

    public List<String> getOptionsForMcq() {

        return createAndSetList("String 1", "String 2");
    }

    public List<String> getOptionsForTof() {

        return createAndSetList("true", "false");
    }

    public List<String> createAndSetList(String option1, String option2)
    {
        List<String> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        return options;
    }

    public boolean isQuestionListTabSelected() {
        return isSelected(QUESTION_LIST_MENU);
    }

    public boolean isQuestionCreateTabSelected() {
        return isSelected(QUESTION_CREATE_MENU);
    }
}
