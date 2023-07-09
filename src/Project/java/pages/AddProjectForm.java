package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {

    private final ITextBox projectName = AqualityServices.getElementFactory()
            .getTextBox(By.id("projectName"),"projectNameInput");
    private final IButton saveProject = AqualityServices.getElementFactory()
            .getButton(By.xpath("//*[@type='submit']"),"saveProjectButton");
    private final ILabel alertSuccess = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//*[contains(@class,'alert-success')]"),"alertSuccessLabel");

    public AddProjectForm() {
        super(By.id("addProject"),"addProjectFormCheckEl");
    }

    public void setTextInProjectName(String name) {
        projectName.clearAndType(name);
    }

    public void clickSaveProject() {
        saveProject.click();
    }

    public boolean isDisplayedAlertSuccess() {
        return alertSuccess.state().waitForDisplayed();
    }
}
