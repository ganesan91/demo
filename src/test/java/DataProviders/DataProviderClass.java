package DataProviders;

import Utils.ApiHelperClass;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    ApiHelperClass apiHelperClass = new ApiHelperClass();

    @DataProvider(name="getQuestionUsingIdApiTestDataProvider")
    public Object[][] getQuestionUsingIdApiTestDataProvider(){
        return new Object[][]{
                {"5d559ec2cb22b639887f2d1a", 200, true},
                {"5d55a36a5995364290e4bc81", 200, true},
                {"5d55a36a5995364290e4bc82", 404, true},
                {"5d55a36a5995364290e4bc81", 401, false},
                {"5d55a47ab6973446273c4c58", 200, true},
                {"5d56123555519c7267280c04", 200, true},
                {"5d56126155519c7267280c06", 200, true},
                {"5d5612a755519c7267280c09", 200, true},
                {"5d563096adf6278527f2d290", 200, true},
                {"5d5632b902a84491ca49adec", 200, true},
                {"5d5638e102a84491ca49adee", 200, true}
        };
    }

    @DataProvider(name="deleteQuestionApiTestDataProvider")
    public Object[][] deleteQuestionApiTestDataProvider(){
        return new Object[][]{
                {"5d580bccfa4e880017257ed7", 200, true},
                {"5d580bccfa4e880017257ed6", 404, true},
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
                {"What is question 5?", "tof", apiHelperClass.createJsonArray("true", "false", true, false), 401, false},
                {"What is question 5?", "tof", apiHelperClass.createJsonArray("true", "false", true, false), 200, true},
                {"What is question 5?", "tof", apiHelperClass.createJsonArray("true", "false", true, true), 400, true},
                {"What is question 5?", "tof", apiHelperClass.createJsonArray("true", "false", false, false), 400, true},
                {"What is question 5?", "tof", apiHelperClass.createJsonArray("text1", "text2", true, false), 400, true},
                {"What is question 6?", "mcq", apiHelperClass.createJsonArray("check1", "check2", true, false), 401, false},
                {"What is question 6?", "mcq", apiHelperClass.createJsonArray("check3", "check4", true, true), 400, true},
                {"What is question 6?", "mcq", apiHelperClass.createJsonArray("check5", "check6", false, false), 400, true},
                {"What is question 6?", "mcq", apiHelperClass.createJsonArray("true", "false", true, false), 200, true},
                {"What is question 6?", "mcq", apiHelperClass.createJsonArray("check7", "check8", true, false), 200, true}
        };
    }

}
