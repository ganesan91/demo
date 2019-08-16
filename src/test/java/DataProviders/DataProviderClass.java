package DataProviders;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name="getQuestionUsingIdApiTestDataProvider")
    public Object[][] getQuestionUsingIdApiTestDataProvider() throws Exception{
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

    @DataProvider(name="loginTestDataProvider")
    public Object[][] loginTestDataProvider() throws Exception{
        return new Object[][]{
                {"test", "test", 200},
                {"test1", "test1", 401}
        };
    }

    @DataProvider(name="getQuestionsApiTestDataProvider")
    public Object[][] getQuestionsApiTestDataProvider() throws Exception{
        return new Object[][]{
                {200, true},
                {401, false}
        };
    }
}
