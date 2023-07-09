package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import consts.Attributes;
import org.openqa.selenium.By;

public class TestInfoPage extends Form {
    private static final String CHECK_ELEMENT = "//ol//li[contains(text(),'%s')]";
    private static final String testInfoField = "//*[contains(@class,'list-group-item-heading') and text()='%s']" +
                    "/following-sibling::*[contains(@class,'list-group-item-text')]";
    private final ILabel log = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//*[contains(@class,'panel-heading') and text()='Logs']/following-sibling::table//td"),
                    "log");
    private final ILabel attachImage = AqualityServices.getElementFactory()
            .getLabel(By.xpath("//*[contains(@class,'panel-heading') and text()='Attachments']/following-sibling::table//img"),
                    "attachImage");

    public TestInfoPage(String testName) {
        super(By.xpath(String.format(CHECK_ELEMENT,testName)), "testPageCheckEl");
    }

    public String getTestFieldText(String field) {
        return AqualityServices.getElementFactory()
                .getLabel(By.xpath(String.format(testInfoField,field)),"testField").getText();
    }

    public String getLog() {
        return log.getText();
    }

    public String getSrcAttachImage() {
        return attachImage.getAttribute(Attributes.SRC.label);
    }
}
