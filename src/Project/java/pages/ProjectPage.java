package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import elements.TestTable;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
public class ProjectPage extends Form {
    private final TestTable testTable = new TestTable(By.xpath("//table[contains(@class,'table')]"),
            By.xpath("//tr[1]//following::tr"),By.tagName("td"),"testTable", ElementState.EXISTS_IN_ANY_STATE);
    private static final String TEST_BUTTON = "//table[contains(@class,'table')]//td//*[text()='%s']";

    public ProjectPage() {
        super(By.xpath("//*[contains(@class,'breadcrumb')]"),"newProjectPageCheckEl");
    }

    public TestInfoPage clickTestButton(String testName) {
        AqualityServices.getElementFactory()
                .getLink(By.xpath(String.format(TEST_BUTTON,testName)),"testButton").click();
        return new TestInfoPage(testName);
    }

    private final ILink homeLink = AqualityServices.getElementFactory().getLink(By.xpath("//ol//li[.='Home']"),"homeLink");

    public MainPage clickHomeLink() {
        homeLink.click();
        return new MainPage();
    }
}
