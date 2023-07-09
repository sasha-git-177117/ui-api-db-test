package steps;

import aquality.selenium.browser.AqualityServices;
import pages.AddProjectForm;
import pages.MainPage;

public class Steps {

    public static void AddProject(String projectName, MainPage mainPage) {
        mainPage.clickAddButton();
        AddProjectForm addProjectForm = mainPage.getAddProjectForm();
        addProjectForm.state().waitForDisplayed();
        AqualityServices.getBrowser().getDriver().switchTo().frame(0);
        addProjectForm.setTextInProjectName(projectName);
        addProjectForm.clickSaveProject();
    }
}
