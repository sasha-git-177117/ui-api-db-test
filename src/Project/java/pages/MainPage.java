package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class MainPage extends Form {

    private final ILabel versionLabel = AqualityServices.getElementFactory().getLabel(
            By.xpath("//*[contains(@class,'footer-text')]//span"),"versionLabel");
    private final IButton addButton = AqualityServices.getElementFactory()
            .getButton(By.xpath("//*[contains(@class,'panel-heading')]//button"),"addButton");
    private final AddProjectForm addProjectForm = new AddProjectForm();
    private static final String PROJECT_ITEMS = "//*[contains(@class,'list-group-item') and text()='%s']";

    public MainPage() {
        super(By.xpath("//*[contains(@class,'list-group')]"),"mainPage");
    }

    public String getVersionText() {
        return versionLabel.getText();
    }

    public void clickAddButton() {
        addButton.click();
    }

    public boolean isDisplayedProjectName(String name) {
        return AqualityServices.getElementFactory()
                .getLabel(By.xpath(String.format(PROJECT_ITEMS,name)),"projectName").state().waitForDisplayed();
    }

    public ProjectPage clickProject(String name) {
        AqualityServices.getElementFactory()
                .getButton(By.xpath(String.format(PROJECT_ITEMS,name)),"projectButton").click();
        return new ProjectPage();
    }
}
