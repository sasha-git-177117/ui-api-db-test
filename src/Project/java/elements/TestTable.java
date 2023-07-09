package elements;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.interfaces.ILabel;
import consts.TestTableEnum;
import models.TestInfoModel;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class TestTable extends Table {
    public TestTable(By loc, By tr, By td, String nameOf, ElementState stateOf) {
        super(loc, tr, td, nameOf, stateOf);
    }

    public List<TestInfoModel> getDataTests() {
        List<TestInfoModel> dataArrayList = new ArrayList<>();
        List<List<String>> allTests = getData();
        for (List<String> data: allTests) {
            TestInfoModel dataTable = new TestInfoModel();
            dataTable.setName(data.get(TestTableEnum.TEST_NAME.label));
            dataTable.setMethod(data.get(TestTableEnum.TEST_METHOD.label));
            dataTable.setStatus(data.get(TestTableEnum.LATEST_TEST_RESULT.label));
            dataTable.setStartTime(data.get(TestTableEnum.LATEST_TEST_START_TIME.label));
            dataTable.setEndTime(data.get(TestTableEnum.LATEST_TEST_END_TIME.label)
                    .equals("") ? "" : data.get(TestTableEnum.LATEST_TEST_END_TIME.label));
            dataTable.setDuration(data.get(TestTableEnum.LATEST_TEST_DURATION.label));
            dataArrayList.add(dataTable);
        }
        return dataArrayList;
    }

    public void waitToTableRefresh() {
        ILabel tableLocator = AqualityServices.getElementFactory().getLabel(getLocator(),"tableLoc");
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.stalenessOf(
                AqualityServices.getBrowser().getDriver().findElement(getLocator())));
        tableLocator.state().waitForDisplayed();
    }
}
