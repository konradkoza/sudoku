package pl.comp.view;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"1 ", "Jakub Januszewicz"},
                {"2 ", "Konrad Koza"}
        };
    }
}
