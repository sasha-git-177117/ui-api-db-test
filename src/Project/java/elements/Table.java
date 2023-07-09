package elements;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import consts.Attributes;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class Table extends Element {
    private By tr;
    private By td;

    public Table(By loc, By tr, By td, String nameOf, ElementState stateOf) {
        super(loc, nameOf, stateOf);
        this.tr = tr;
        this.td = td;
    }

    protected List<List<String>> getData() {
        ILabel table = AqualityServices.getElementFactory().getLabel(this.getLocator(),"table");
        List<List<String>> elements = new ArrayList<>();

        List<ILabel> rows = table.findChildElements(this.tr,ElementType.LABEL);
        for (ILabel row : rows)
        {
            ArrayList<String> data = new ArrayList<>();
            List<ILabel> columns = row.findChildElements(this.td,ElementType.LABEL);
            for (ILabel column: columns)
            {
                data.add(column.getText());
            }
            elements.add(data);
        }
        return elements;
    }

    @Override
    protected String getElementType() {
        return Attributes.TABLE.label;
    }
}
