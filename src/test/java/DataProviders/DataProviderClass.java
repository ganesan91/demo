package DataProviders;

import Utils.ApiHelperClass;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    ApiHelperClass apiHelperClass = new ApiHelperClass();

    @DataProvider(name="getQuestionUsingIdApiTestDataProvider")
    public Object[][] getQuestionUsingIdApiTestDataProvider(){
        return new Object[][]{
                {"5d580bccfa4e880017257ed7", 200, true},
                {"5d55a36a5995364290e4bc82", 404, true},
                {"5d55a36a5995364290e4bc81", 401, false}
        };
    }

    @DataProvider(name="deleteQuestionApiTestDataProvider")
    public Object[][] deleteQuestionApiTestDataProvider(){
        return new Object[][]{
                {"5d580bccfa4e880017257ed7", 200, true},
                {"5d580bccfa4e880017257ed6", 404, true},
                {"", 404, true},
                {"5d55a36a5995364290e4bc81", 401, false}
        };
    }

    @DataProvider(name="loginTestDataProvider")
    public Object[][] loginTestDataProvider(){
        return new Object[][]{
                {"test", "", 400},
                {"", "test", 400},
                {"test1", "test1", 401}
        };
    }

    @DataProvider(name="getQuestionsApiTestDataProvider")
    public Object[][] getQuestionsApiTestDataProvider(){
        return new Object[][]{
                {200, true},
                {401, false}
        };
    }

    @DataProvider(name="createQuestionApiTestDataProvider")
    public Object[][] createQuestionApiTestDataProvider(){
        return new Object[][]{
                {"What is question 7?", "tof", apiHelperClass.createJsonArray("true", "false", true, false), 401, false},
                {"What is question 8?", "tof", apiHelperClass.createJsonArray("true", "false", true, false), 200, true},
                {"", "tof", apiHelperClass.createJsonArray("true", "false", true, false), 400, true},
                {"What is question 9?", "", apiHelperClass.createJsonArray("true", "false", true, false), 400, true},
                {"What is question 9?", "tof", apiHelperClass.createJsonArray("", "false", true, false), 400, true},
                {"What is question 9?", "tof", apiHelperClass.createJsonArray("true", "", true, false), 400, true},
                {"What is question 9?", "tof", apiHelperClass.createJsonArray("true", "false", true, true), 400, true},
                {"What is question 10?", "tof", apiHelperClass.createJsonArray("true", "false", false, false), 400, true},
                {"What is question 11?", "tof", apiHelperClass.createJsonArray("text1", "text2", true, false), 400, true},
                {"What is question 12?", "mcq", apiHelperClass.createJsonArray("check1", "check2", true, false), 401, false},
                {"What is question 13?", "mcq", apiHelperClass.createJsonArray("check3", "check4", true, true), 400, true},
                {"What is question 13?", "mcq", apiHelperClass.createJsonArray("", "check4", true, false), 400, true},
                {"What is question 13?", "mcq", apiHelperClass.createJsonArray("check3", "", true, false), 400, true},
                {"What is question 14?", "mcq", apiHelperClass.createJsonArray("check5", "check6", false, false), 400, true},
                {"What is question 15?", "mcq", apiHelperClass.createJsonArray("true", "false", true, false), 200, true},
                {"What is question 16?", "mcq", apiHelperClass.createJsonArray("check7", "check8", true, false), 200, true}
        };
    }

}
