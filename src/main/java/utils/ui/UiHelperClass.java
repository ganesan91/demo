package utils.ui;

public class UiHelperClass extends UiBaseClass{

    public UiHelperClass() {

    }

    public String getCurrentUrl()
    {
        return getDriver().getCurrentUrl();
    }

}
